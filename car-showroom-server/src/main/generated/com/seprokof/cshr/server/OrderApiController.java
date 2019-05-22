package com.seprokof.cshr.server;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.Optional;
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2019-05-22T12:59:53.812+03:00[Europe/Moscow]")

@Controller
@RequestMapping("${openapi.carShowroom.base-path:}")
public class OrderApiController implements OrderApi {

    private final OrderApiDelegate delegate;

    public OrderApiController(@org.springframework.beans.factory.annotation.Autowired(required = false) OrderApiDelegate delegate) {
        this.delegate = Optional.ofNullable(delegate).orElse(new OrderApiDelegate() {});
    }

    @Override
    public OrderApiDelegate getDelegate() {
        return delegate;
    }

}
