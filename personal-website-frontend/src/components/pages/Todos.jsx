import React, { useState, useEffect } from 'react';

const Todos = ({ userId }) => {
    const [todos, setTodos] = useState([]);
    const [userName, setUserName] = useState('');
    const [newTodo, setNewTodo] = useState({ category: '', task: '', description: '' });
    const [filterCategory, setFilterCategory] = useState('all');
    const [selectedTodos, setSelectedTodos] = useState([]);
    const [error, setError] = useState('');

    useEffect(() => {
        // Fetch user details
        fetch(`http://localhost:8080/api/v1/users/${userId}`)
            .then(response => response.json())
            .then(data => setUserName(data.name))
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
        <div className="container mx-auto p-4">
            <h1 className="text-2xl mb-4 text-white">To-do List for {userName}</h1>
            {error && <p className="text-red-500">{error}</p>}
            <div className="mb-4 flex space-x-2">
                <button onClick={handleDeleteSelected} className="bg-red-500 text-white px-4 py-2 rounded">
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
                        <th className="py-2">Select</th>
                        <th className="py-2">Label</th>
                        <th className="py-2">Task</th>
                        <th className="py-2">Description</th>
                        <th className="py-2">Status</th>
                    </tr>
                    </thead>
                    <tbody>
                    {filteredTodos.map(todo => (
                        <tr key={todo.id}>
                            <td className="border px-4 py-2">
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
                            <td className="border px-4 py-2">{todo.category}</td>
                            <td className="border px-4 py-2">{todo.task}</td>
                            <td className="border px-4 py-2">{todo.description}</td>
                            <td className="border px-4 py-2">
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
            <div className="mt-4 bg-gray-800 p-4 rounded-lg">
                <h2 className="text-lg mb-2 text-white">Add a new Todo</h2>
                <div className="mb-2">
                    <label className="block text-sm font-bold mb-1 text-white">Category</label>
                    <input
                        type="text"
                        value={newTodo.category}
                        onChange={e => setNewTodo({ ...newTodo, category: e.target.value })}
                        className="border p-2 rounded w-full"
                    />
                </div>
                <div className="mb-2">
                    <label className="block text-sm font-bold mb-1 text-white">Task</label>
                    <input
                        type="text"
                        value={newTodo.task}
                        onChange={e => setNewTodo({ ...newTodo, task: e.target.value })}
                        className="border p-2 rounded w-full"
                    />
                </div>
                <div className="mb-2">
                    <label className="block text-sm font-bold mb-1 text-white">Description</label>
                    <input
                        type="text"
                        value={newTodo.description}
                        onChange={e => setNewTodo({ ...newTodo, description: e.target.value })}
                        className="border p-2 rounded w-full"
                    />
                </div>
                <button onClick={handleAddTodo} className="bg-blue-500 text-white px-4 py-2 rounded">
                    Add Todo
                </button>
            </div>
        </div>
    );
};

export default Todos;