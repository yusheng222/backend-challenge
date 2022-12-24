# backend-challenge

### Assumptions
JWT Token only contains user's identifier, and the encrypt algorithm is HMAC256.

The relation between customer and accounts should be stored in a database.

The customer searching Service and account searching should implement in another modules, and invoked by RPC. However, what here needs only those two function, to make it easy, I just implemented those service in the same module.

And some parts like common DTO and utils package should be placed at a common module, same reason, I implemented them all together.