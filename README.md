# To-Do List Back-End Server

This server is a back-end server to be used for your front-end UI development practice.

The To-Do List Back-End server includes an in-memory database and is meant to be used locally.

## Models

### ToDoList Object
To-Do List Object:
```json
{
  "id": 1,
  "tasks": []
}
```

**Properties**
- `id` - Type: `number` 
  - ID of the to-do list.
- `tasks` - Type: `Task[]` 
  - Array of [tasks](#task-object)

### Task Object
Task object:

```json
{
  "id": 1,
  "description": "Something to do.",
  "completed": false,
  "highPriority": false,
  "deleted": false
}
```

**Properties:**
- `id` - Type: `number` 
  - ID of the task.
- `description` - Type: `string`
  - Description of task.
- `completed` - Type: `boolean`
  - `true` for a completed task. `false` if the task in not completed.
- `highPriority` - Type: `boolean`
  - `true` for a high-priority. `false` for a normal task.
- `deleted` - Type: `boolean`
  - Set this flag to `true` when saving a to-do list to delete this task.

## Requests

Retrieve to-do lists:
```http request
GET http://localhost:8080/to-do-lists
```

This will return a list of to-do lists currently in your database.

Retrieve a single to-do list:
```http request
GET http://localhost: 8080/to-do-lists/{{toDoListId}}
```

This will return a to-do list with the provided ID.

Retrieve a task:
```http request
GET http://localhost: 8080/to-do-lists/{{toDoListId}}/tasks/{{taskId}}}}
```

This will return a task object from the specified to-do list. If the task object does not exist within the to-do list (even if the task ID exists in the database) it will return a 404 not found status code.


To create or save a to-do list, `POST` to the `/to-do-lists` endpoint of this server. The endpoint accepts a JSON of a [ToDoList](#todolist-object) object.

```http request
POST http://localhost:8080/to-do-lists
Content-Type: application/json
```

Example Request:
```json
{
  "tasks": []
}
```
Creates a brand-new empty to-do list.

Example Response:
```json
{
  "id": 1,
  "tasks": []
}
```

Example Request (With Tasks):
```json
{
  "tasks": [
    {
      "description": "Get groceries at the grocery store.",
      "highPriority": true
    },
    {
      "description": "Take dog to the park."
    }
  ]
}
```
Creates a brand-new to-do list with existing items.

Example Response (With Tasks):
```json
{
  "id": 1,
  "tasks": [
    {
      "id": 1,
      "description": "Get groceries at the grocery store.",
      "highPriority": true,
      "completed": false,
      "deleted": false
    },
    {
      "id":  2,
      "description": "Take dog to the park.",
      "completed": false,
      "deleted": false
    }
  ]
}
```


When saving your to-do lists, if an ID is included, it will update a record with the ID you provided with the new information. If no ID is provided, then a new record will be created.

Deleting a task from a to-do list is as simple as setting the `deleted` property of the [Task object](#task-object) to `true` and posting the to-do list.

Example Request for Deleting:

```json
{
  "id": 1,
  "tasks": [
    {
      "id": 2,
      "description": "Take dog to the park.",
      "completed": true,
      "highPriority": false,
      "deleted": true
    }
  ]
}
```

This request will delete task 2. _(**Note:** The other properties are not required, but your front-end should be sending the whole object back anyway if you are using an editable model.)_


## Important Information

Your front-end should be running on your local host using ports `4200` or `3000`. CORS is enabled for this server.

Again, this server uses an **in-memory database** so the data you persist in a session will be deleted when the server stops or restarts.