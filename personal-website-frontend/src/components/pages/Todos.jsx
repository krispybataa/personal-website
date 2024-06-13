import React, { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
import shan from '../../assets/sheianne.png';
import clark from '../../assets/clark.png';


const Todos = ({ userId }) => {
    const [todos, setTodos] = useState([]);
    const [userName, setUserName] = useState('');
    const [userImage, setUserImage] = useState('');
    const [newTodo, setNewTodo] = useState({ category: '', task: '', description: '' });
    const [filterCategory, setFilterCategory] = useState('all');
    const [selectedTodos, setSelectedTodos] = useState([]);
    const [error, setError] = useState('');

    useEffect(() => {
        // Fetch user details
        fetch(`http://localhost:8080/api/v1/users/${userId}`)
            .then(response => response.json())
            .then(data => {
                setUserName(data.name);
                // Determine the user image based on the user name or other data property
                if (data.name === 'Sheianne') {
                    setUserImage(shan);
                } else if (data.name === 'Clark') {
                    setUserImage(clark);
                }
            })
            .catch(error => setError('Error fetching user details: ' + error));

        // Fetch todos
        fetchTodos();
    }, [userId]);

    const fetchTodos = () => {
        fetch(`http://localhost:8080/api/v1/todos/users/${userId}`)
            .then(response => response.json())
            .then(data => setTodos(Array.isArray(data) ? data : []))
            .catch(error => setError('Error fetching todos: ' + error));
    };

    const handleAddTodo = () => {
        fetch(`http://localhost:8080/api/v1/todos`, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ ...newTodo, users: { id: userId } })
        })
            .then(() => {
                setNewTodo({ category: '', task: '', description: '' });
                fetchTodos();
            })
            .catch(error => setError('Error adding todo: ' + error));
    };

    const handleDeleteSelected = () => {
        selectedTodos.forEach(todoId => {
            fetch(`http://localhost:8080/api/v1/todos/${todoId}`, {
                method: 'DELETE'
            })
                .then(() => fetchTodos())
                .catch(error => setError('Error deleting todo: ' + error));
        });
        setSelectedTodos([]);
    };

    const handleCompletionToggle = (todoId, completed) => {
        fetch(`http://localhost:8080/api/v1/todos/${todoId}/complete?completed=${completed}`, {
            method: 'PUT',
        })
            .then(() => fetchTodos())
            .catch(error => setError('Error updating todo: ' + error));
    };

    const filteredTodos = todos.filter(todo => {
        if (filterCategory !== 'all') return todo.category === filterCategory;
        return true;
    });

    const uniqueCategories = [...new Set(todos.map(todo => todo.category))];

    return (
        <div className="container todo-container mx-auto p-4">
            <h1 className="to-do-header text-2xl mb-4 text-white">To-do List for {userName}</h1>
            {error && <p className="text-red-500">{error}</p>}
            <div className="gen-todo-container">
                <div className="todo-container-left">
                    <div className="mb-4 flex space-x-2">
                        <button onClick={handleDeleteSelected}
                                className="handle-deleted bg-red-500 text-white px-4 py-2 rounded">
                            Delete Selected
                        </button>
                        <select
                            value={filterCategory}
                            onChange={e => setFilterCategory(e.target.value)}
                            className="border p-2 rounded"
                        >
                            <option value="all">All Labels</option>
                            {uniqueCategories.map(category => (
                                <option key={category} value={category}>{category}</option>
                            ))}
                        </select>
                    </div>
                    <div className="overflow-x-auto">
                        <table className="min-w-full bg-white">
                            <thead>
                            <tr>
                                <th className="py-2 todo-check">Select</th>
                                <th className="py-2 todo-label">Label</th>
                                <th className="py-2 todo-task">Task</th>
                                <th className="py-2 todo-description">Description</th>
                                <th className="py-2 todo-check">Status</th>
                            </tr>
                            </thead>
                            <tbody>
                            {filteredTodos.map(todo => (
                                <tr key={todo.id}>
                                    <td className="border px-4 py-2 center-content select-todo">
                                        <input
                                            type="checkbox"
                                            checked={selectedTodos.includes(todo.id)}
                                            onChange={() => {
                                                if (selectedTodos.includes(todo.id)) {
                                                    setSelectedTodos(selectedTodos.filter(id => id !== todo.id));
                                                } else {
                                                    setSelectedTodos([...selectedTodos, todo.id]);
                                                }
                                            }}
                                        />
                                    </td>
                                    <td className="border px-4 py-2 label-todo">{todo.category}</td>
                                    <td className="border px-4 py-2 task-todo">{todo.task}</td>
                                    <td className="border px-4 py-2 description-todo">{todo.description}</td>
                                    <td className="border px-4 py-2 center-content">
                                        <input
                                            type="checkbox"
                                            checked={todo.completed}
                                            onChange={() => handleCompletionToggle(todo.id, !todo.completed)}
                                        />
                                    </td>
                                </tr>
                            ))}
                            </tbody>
                        </table>
                    </div>
                    <div className="mt-4 bg-gray-800 p-4 rounded-lg add-todo">
                        <h2 className="text-lg mb-2 text-white">Add a new Todo</h2>
                        <div className="mb-2">
                            <label className="block text-sm font-bold mb-1 text-white">Category</label>
                            <input
                                type="text"
                                value={newTodo.category}
                                onChange={e => setNewTodo({...newTodo, category: e.target.value})}
                                className="border p-2 rounded w-full"
                            />
                        </div>
                        <div className="mb-2">
                            <label className="block text-sm font-bold mb-1 text-white">Task</label>
                            <input
                                type="text"
                                value={newTodo.task}
                                onChange={e => setNewTodo({...newTodo, task: e.target.value})}
                                className="border p-2 rounded w-full"
                            />
                        </div>
                        <div className="mb-2">
                            <label className="block text-sm font-bold mb-1 text-white">Description</label>
                            <input
                                type="text"
                                value={newTodo.description}
                                onChange={e => setNewTodo({...newTodo, description: e.target.value})}
                                className="border p-2 rounded w-full"
                            />
                        </div>
                        <button onClick={handleAddTodo} className="bg-blue-500 text-white px-4 py-2 rounded">
                            Add Todo
                        </button>
                    </div>
                </div>
                <div className="todo-container-right">
                    <h1 className="watching">Be productive, {userName} is watching you!</h1>
                    <div className="box">
                        <div className="profile-section">
                            <img src={userImage} alt={userName} className="todo-pfp"/>
                        </div>
                    </div>
                </div>
            </div>

            <div className="todo-buttons">
                <div className="pages">
                    <Link to={`/${userName}/music`} className="">
                        <button>
                            Favorite Music
                        </button>
                    </Link>
                </div>
                <div className="pages">
                    <Link to={`/${userName}/hobbies`} className="">
                        <button className="">
                            Hobbies
                        </button>
                    </Link>
                </div>
            </div>
        </div>
    );
};

export default Todos;
