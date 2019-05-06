package com.seprokof.cshr.server;

import com.seprokof.cshr.server.ErrorMessage;
import com.seprokof.cshr.server.Order;
import io.swagger.annotations.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * A delegate to be called by the {@link OrderApiController}}.
 * Implement this interface with a {@link org.springframework.stereotype.Service} annotated class.
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2019-05-06T15:17:55.853298200+03:00[Europe/Moscow]")

public interface OrderApiDelegate {

    default Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }

    /**
     * @see OrderApi#createOrder
     */
    default ResponseEntity<Void> createOrder(Order order) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * @see OrderApi#deleteOrder
     */
    default ResponseEntity<Void> deleteOrder(String orderId) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * @see OrderApi#getAllOrders
     */
    default ResponseEntity<List<Order>> getAllOrders() {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    ApiUtil.setExampleResponse(request, "application/json", "{  \"car\" : {    \"brand\" : \"Audi\",    \"model\" : \"RS5\"  },  \"client\" : {    \"firstName\" : \"Sergey\",    \"lastName\" : \"Prokofiev\",    \"phone\" : \"89092777920\",    \"vip\" : true  },  \"clientPhone\" : \"89092777920\"}");
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * @see OrderApi#getOrder
     */
    default ResponseEntity<Order> getOrder(String orderId) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    ApiUtil.setExampleResponse(request, "application/json", "{  \"car\" : {    \"brand\" : \"Audi\",    \"model\" : \"RS5\"  },  \"client\" : {    \"firstName\" : \"Sergey\",    \"lastName\" : \"Prokofiev\",    \"phone\" : \"89092777920\",    \"vip\" : true  },  \"clientPhone\" : \"89092777920\"}");
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * @see OrderApi#updateOrder
     */
    default ResponseEntity<Void> updateOrder(String orderId,
        Order order) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

}
