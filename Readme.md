<Html>
    <h1>Local Social code Guide</h1>
    <li>We are using multi layer architecture for this project </li> 
    <li>Main layers are Controller , Services , Repositories</li>
<br>
    <h3 style="color:pink;">Controller</h3>
    <li>To add any controller take reference of Auth Controller</li>
    <li>Request body and response body signature should be added in DTOS package </li>
    <li>A controller can be private or public , public meaning it can be accessed without token</li>
    <li>A controller should have handling of request and response headers/body , do not add any logic in controller</li>
    <li>For request validation use Annotation like @valaid and add annotation in DTOS as well</li>
    <br>
    <h3 style="color:pink;">Service</h3>
    <li>Create any services in service package, take reference of Auth service</li>
    <li>All business logic should be present in service layer </li>
    <l1>Services can be called from controller or another service</l1>
    <li>A service can call other services and repositories</li>
<br>
    <h3 style="color:pink;">Dtos</h3>
    <li>Dtos are data transfer objects</li>
<li>Dtos does not contains any business logic , it only contails plain java object that can used for data movement from controller to service and vice versa</li>
<li>We are using JWT tokens</li>
<br>
    <h3 style="color:pink;">Repository</h3>
    <li>This layer is used to interact with databases</li>
    <li>Use Jpa for simple queries and jdbc template for complex queries </li>

<br>
    <h3 style="color:pink;">Entity</h3>
    <li>Entity refer to class and table mapping</li>
<li>Any new table should be added in Entity Package</li>


<br>
    <h3 style="color:pink;">Security</h3>
    <li>Security package is responsible for authorization and authentication purpose</li>
<li>We are using JWT tokens</li>
</Html>