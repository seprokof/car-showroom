/**
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech) (3.3.4).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */
package com.seprokof.cshr.server;

import com.seprokof.cshr.server.ErrorMessage;
import com.seprokof.cshr.server.Order;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;
import java.util.Map;
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2019-05-06T15:17:55.853298200+03:00[Europe/Moscow]")

@Validated
@Api(value = "order", description = "the order API")
public interface OrderApi {

    default OrderApiDelegate getDelegate() {
        return new OrderApiDelegate() {};
    }

    @ApiOperation(value = "", nickname = "createOrder", notes = "Creates an order to buy a car.", tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 201, message = "Order created"),
        @ApiResponse(code = 500, message = "Unexpected error", response = ErrorMessage.class) })
    @RequestMapping(value = "/order",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.POST)
    default ResponseEntity<Void> createOrder(@ApiParam(value = "Order data such as client or reference using phone number and particular car." ,required=true )  @Valid @RequestBody Order order) {
        return getDelegate().createOrder(order);
    }


    @ApiOperation(value = "", nickname = "deleteOrder", notes = "Deletes an order.", tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Order deleted successfully"),
        @ApiResponse(code = 500, message = "Unexpected error", response = ErrorMessage.class) })
    @RequestMapping(value = "/order/{orderId}",
        produces = { "application/json" }, 
        method = RequestMethod.DELETE)
    default ResponseEntity<Void> deleteOrder(@ApiParam(value = "Unique identifier of order.",required=true) @PathVariable("orderId") String orderId) {
        return getDelegate().deleteOrder(orderId);
    }


    @ApiOperation(value = "", nickname = "getAllOrders", notes = "Retrieves information about all orders.", response = Order.class, responseContainer = "List", tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Successful operation", response = Order.class, responseContainer = "List"),
        @ApiResponse(code = 500, message = "Unexpected error", response = ErrorMessage.class) })
    @RequestMapping(value = "/order",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    default ResponseEntity<List<Order>> getAllOrders() {
        return getDelegate().getAllOrders();
    }


    @ApiOperation(value = "", nickname = "getOrder", notes = "Retrieves information about particular order.", response = Order.class, tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Successful operation", response = Order.class),
        @ApiResponse(code = 500, message = "Unexpected error", response = ErrorMessage.class) })
    @RequestMapping(value = "/order/{orderId}",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    default ResponseEntity<Order> getOrder(@ApiParam(value = "Unique identifier of order.",required=true) @PathVariable("orderId") String orderId) {
        return getDelegate().getOrder(orderId);
    }


    @ApiOperation(value = "", nickname = "updateOrder", notes = "Updates an order.", tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Order updated successfully"),
        @ApiResponse(code = 500, message = "Unexpected error", response = ErrorMessage.class) })
    @RequestMapping(value = "/order/{orderId}",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.PUT)
    default ResponseEntity<Void> updateOrder(@ApiParam(value = "Unique identifier of order.",required=true) @PathVariable("orderId") String orderId,@ApiParam(value = "Order data such as client or reference using phone number and particular car." ,required=true )  @Valid @RequestBody Order order) {
        return getDelegate().updateOrder(orderId, order);
    }

}