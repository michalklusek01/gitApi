
# gitApi

This is a simple REST API application built with Java and Spring. It allows users to retrieve information about GitHub repositories based on the provided username.

## Requirements

- Java 17
- Spring Framework 3
- Maven

## Usage
The API provides a single endpoint:

```
GET /api/repos/{username}
```

### Request

* *username* (path parameter): The GitHub username for which to retrieve the repositories.

The following headers can be used in the request:
* *Accept*: The desired response format (application/json).

### Response 
If the request is successful and the user exists, the API will return a list of repositories in the specified format (JSON). The response will include the repository name, owner login, and information about each branch (name and last commit SHA).

If the user does not exist, the API will return a 404 status code with an error message.

If the request specifies an unsupported media type (application/xml), the API will return a 406 status code with an error message.

## Examples

**Get repositories for a user (JSON):**

*GET /api/repos/michalklusek01*

*Accept: application/json*

Response:
```
[
  {
    "name": "repository1",
    "owner": "michalklusek01",
    "branches": [
      {
        "name": "branch1",
        "lastCommitSha": "abcd1234"
      },
      {
        "name": "branch2",
        "lastCommitSha": "efgh5678"
      }
    ]
  },
  {
    "name": "repository2",
    "owner": "michalklusek01",
    "branches": [
      {
        "name": "branch3",
        "lastCommitSha": "ijkl9012"
      }
    ]
  }
]
```
**User not found (JSON):**
```
GET /api/repos/nonexistentuser
Accept: application/json
```

Response:
```
{
  "status": 404,
  "message": "User not found: nonexistentuser"
}
```
**Unsupported media type (XML):**
```
<error>
  <status>406</status>
  <message>Unsupported Media Type</message>
</error>
```
