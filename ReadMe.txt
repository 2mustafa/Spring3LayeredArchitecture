Using a layered architecture in Spring applications,
where each layer has a specific responsibility:

- DAO Layer: Responsible for data access and interaction with the database.
- Service Layer: Responsible for business logic and orchestrating data access.
- Controller Layer: Responsible for handling HTTP requests and responses, acting as the entry point for web requests.

1. **Database Configuration:**
    - Configure database properties in `application.properties`.
    - Specify the database URL, username, password, and other relevant properties.


2. **Define the Entity (Employee):**
   - Create the `Employee` entity class with appropriate annotations for database mapping.

3. **Create the DAO Interface (EmployeeDAO):**
   - Define the `EmployeeDAO` interface with a `findAll()` method for data access.


4. **Implement the DAO (EmployeeDAOImplement):**
   - In `EmployeeDAOImplement`:
     1. Define a field for `EntityManager`.
     2. Inject the `EntityManager` using constructor injection.
     3. Override the `findAll()` method:
        - Create a query using `TypedQuery`.
        - Execute the query and get the result list.
        - Return the result list.

5. **Create the Service Interface (EmployeeService):**
   - Define the `EmployeeService` interface with all methods from the DAO interface.


6. **Implement the Service (EmployeeServiceImpl):**
   - In `EmployeeServiceImpl`, inject the `EmployeeDAO` using constructor injection.
   - Implement the methods, forwarding the calls to the DAO.


7. **Create the REST Controller (EmployeeRestController):
   - Define the EmployeeRestController class.
   - Annotate it with @RestController.
   - Create a constructor for EmployeeRestController.
   - Inject the EmployeeService.
   - Define methods for various CRUD operations along with their corresponding endpoints:
        - findAll() method: Annotated with @GetMapping("/employees") to retrieve all employees.
        - getEmployee() method: Annotated with @GetMapping("/employees/{employeeId}") to retrieve an employee by ID.
        - addEmployee() method: Annotated with @PostMapping("/employees") to add a new employee.
        - updateEmployee() method: Annotated with @PutMapping("/employees") to update an existing employee.
        - deleteEmployee() method: Annotated with @DeleteMapping("/employees/{employeeId}") to delete an employee by ID.


8. **Test the Spring Boot Application: run the DemoApplication**






## "Key Dependency Injection Patterns in a Spring Boot Application" ##

1. In the DAO implementation:
   inject EntityManager, and create queries connected to it.

2. In the service implementation:
   inject the DAO interface and have each overridden method return the corresponding DAO interface method.

3. In the REST controller:
   inject the service interface, and return service interface methods in @GetMapping endpoints.






## EntityManager ##

The EntityManager in JPA provides a set of commonly used methods for performing CRUD operations on entities.
Here are some of them:

    persist(entity):
    Used to add a new entity to the persistence context, which eventually gets inserted into the database upon transaction commit.

        public void addEmployee(Employee employee) {
            entityManager.persist(employee);
        }



    find(entityClass, primaryKey):
    Retrieves an entity by its primary key. It's used to fetch an entity from the database based on its unique identifier.

    public Employee getEmployeeById(int id) {
        return entityManager.find(Employee.class, id);
    }



    merge(entity):
    Updates an existing entity or inserts a new one if the entity is not managed by the persistence context.

        public Employee updateEmployee(Employee employee) {
            return entityManager.merge(employee);
        }



    remove(entity):
    Deletes an entity from the database.

        public void deleteEmployee(Employee employee) {
            entityManager.remove(employee);
        }



    flush():
    Synchronizes the changes made in the persistence context with the database.
    This method can be used to force the write of pending changes to the database before a transaction is committed.

        public void saveAndFlush(Employee employee) {
            entityManager.persist(employee);
            entityManager.flush();
        }



    createQuery(jpql, resultClass):
    Creates a JPA query using the Java Persistence Query Language (JPQL) and returns a TypedQuery object for executing the query.

        public List<Employee> getEmployeesByLastName(String lastName) {
            TypedQuery<Employee> query = entityManager.createQuery(
                "SELECT e FROM Employee e WHERE e.lastName = :lastName", Employee.class);
            query.setParameter("lastName", lastName);
            return query.getResultList();
        }



    getReference(entityClass, primaryKey):
    Returns a reference (proxy) to an entity without actually loading it from the database until a method is invoked on it. Useful for lazy loading.

        public Employee getEmployeeReference(int id) {
            return entityManager.getReference(Employee.class, id);
        }
