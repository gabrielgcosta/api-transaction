# API transaction
This is an API developed in a simplified way, to carry out monetary transactions between users.

# Users API endpoints

These endpoints allow you to handle Users register, and get all Users.

## GET
[/users](#get-users) <br/>

## POST
[/users](#post-users) <br/>
___

### GET /users
Get all users data

**Response**

```
[
    {
        "id": "99bed013-f866-4b80-9d4e-4d8282c7a5f0",
        "firstName": "firstNameUser1",
        "lastName": "lastNameUser1",
        "document": "123456789",
        "email": "user1@example.com",
        "password": "password",
        "balance": 100.00,
        "userType": "COMMON"
    },
    {
        "id": "297b5945-2830-4c3b-9154-adc8ef30a9d6",
        "firstName": "firstNameUser2",
        "lastName": "lastNameUser2",
        "document": "123456789",
        "email": "user2@example.com",
        "password": "password",
        "balance": 100.00,
        "userType": "MERCHANT"
    }
]
```
### POST /users
Register an user.

**Parameters**

|          Name | Required |  Type   | Description                                                                                                                                                           |
| -------------:|:--------:|:-------:| --------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| `firstName` | required | string  | First name of the user                                                                     |
| `secondName` | required | string  | Second name of the user                                                                     |
| `document` | required / unique | string | User's document |
| `balance` | required | float | Balance of user's account |
| `email` | required / unique | string | User's email |
| `password` | required | string | User's password |
| `userType` | required | string | Type of the user. Accepted options: COMMON / MERCHANT |

**Response**
```
{
  "id": "99bed013-f866-4b80-9d4e-4d8282c7a5f0",
  "firstName": "firstNameUser1",
  "lastName": "lastNameUser1",
  "document": "123456789",
  "email": "user1@example.com",
  "password": "password",
  "balance": 100.00,
  "userType": "COMMON"
}
```

# Transactions API endpoints

These endpoints allow you to handle creating a transaction, and get all transactions.

## GET
[/transactions](#get-transactions) <br/>

## POST
[/transactions](#post-transactions) <br/>
___

### GET /transactions
Get all transactions data

**Response**

```
{
  "id": "936139b8-b83f-4a91-a0d8-b16761537569",
  "amount": 10,
  "sender": {
        "id": "99bed013-f866-4b80-9d4e-4d8282c7a5f0",
        "firstName": "firstNameUser1",
        "lastName": "lastNameUser1",
        "document": "123456789",
        "email": "user1@example.com",
        "password": "password",
        "balance": 100.00,
        "userType": "COMMON"
  },
  "receiver": {
        "id": "297b5945-2830-4c3b-9154-adc8ef30a9d6",
        "firstName": "firstNameUser2",
        "lastName": "lastNameUser2",
        "document": "123456789",
        "email": "user2@example.com",
        "password": "password",
        "balance": 100.00,
        "userType": "MERCHANT"
  },
  "timeStamp": "2024-02-22T17:32:29.477399576"
}
```
### POST /transactions
Make a transaction

**Rules**
- The API validates that the user making the transaction has the amount value in his balance account
- The API gets a authorization for the transaction with a third part endpoint, the transaction only goes thru if the endpoint's response is 'true'

**Parameters**

|          Name | Required |  Type   | Description                                                                                                                                                           |
| -------------:|:--------:|:-------:| --------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| `value` | required | float  | Value of the transaction                                                                     |
| `senderId` | required | string  | Id of the user that are doing the transaction                                                                    |
| `receiverId` | required | string | Id of the user that are receiving the transaction |

```
{
  "id": "936139b8-b83f-4a91-a0d8-b16761537569",
  "amount": 10,
  "sender": {
        "id": "99bed013-f866-4b80-9d4e-4d8282c7a5f0",
        "firstName": "firstNameUser1",
        "lastName": "lastNameUser1",
        "document": "123456789",
        "email": "user1@example.com",
        "password": "password",
        "balance": 100.00,
        "userType": "COMMON"
  },
  "receiver": {
        "id": "297b5945-2830-4c3b-9154-adc8ef30a9d6",
        "firstName": "firstNameUser2",
        "lastName": "lastNameUser2",
        "document": "123456789",
        "email": "user2@example.com",
        "password": "password",
        "balance": 100.00,
        "userType": "MERCHANT"
  },
  "timeStamp": "2024-02-22T17:32:29.477399576"
}
```
