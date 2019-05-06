package com.seprokof.cshr.server;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.seprokof.cshr.server.Car;
import com.seprokof.cshr.server.Client;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Order
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2019-05-06T15:17:55.853298200+03:00[Europe/Moscow]")

public class Order  implements Serializable {
  private static final long serialVersionUID = 1L;

  @JsonProperty("clientPhone")
  private String clientPhone;

  @JsonProperty("client")
  private Client client = null;

  @JsonProperty("car")
  private Car car = null;

  public Order clientPhone(String clientPhone) {
    this.clientPhone = clientPhone;
    return this;
  }

  /**
   * Get clientPhone
   * @return clientPhone
  */
  @ApiModelProperty(example = "89092777920", required = true, value = "")
  @NotNull


  public String getClientPhone() {
    return clientPhone;
  }

  public void setClientPhone(String clientPhone) {
    this.clientPhone = clientPhone;
  }

  public Order client(Client client) {
    this.client = client;
    return this;
  }

  /**
   * Get client
   * @return client
  */
  @ApiModelProperty(value = "")

  @Valid

  public Client getClient() {
    return client;
  }

  public void setClient(Client client) {
    this.client = client;
  }

  public Order car(Car car) {
    this.car = car;
    return this;
  }

  /**
   * Get car
   * @return car
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull

  @Valid

  public Car getCar() {
    return car;
  }

  public void setCar(Car car) {
    this.car = car;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Order order = (Order) o;
    return Objects.equals(this.clientPhone, order.clientPhone) &&
        Objects.equals(this.client, order.client) &&
        Objects.equals(this.car, order.car);
  }

  @Override
  public int hashCode() {
    return Objects.hash(clientPhone, client, car);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Order {\n");
    
    sb.append("    clientPhone: ").append(toIndentedString(clientPhone)).append("\n");
    sb.append("    client: ").append(toIndentedString(client)).append("\n");
    sb.append("    car: ").append(toIndentedString(car)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

