### Thread per request model- Blocking APIs:
When a client makes a call, a thread from the thread pool is assigned to the request, and that thread is responsible for handling the request and sending the response to the client.
The thread that has been assigned is going to be blocked until the client gets a response.

This model won´t scale for today's application needs -> Why? : Because today's applications usually interact with multiple other services and data sources. Each call to an external service or data base adds to the application latency and could be a blocking point.

To resolve this issue, we can implement parallel calls to each external service or data base using Callbacks or Future. These Java APIs allow us to make asynchronous calls. However, they are hard to implement and read, and in the case of Future, the method Future.get() is a blocking call.

### Reactive programming:
Any code that you write in reactive programming is essentially asynchronous and non-blocking, and the data will flow as an event/messsage driven stream.

##### Reactive Streams Specification:
✔Publisher: the data source (data base, remote/external service, etc)

✔Subscriber

✔Subscription: connects the publisher and subscriber

✔Processor

###### We can implement reactive programming using Spring WebFlux and Project Reactor.