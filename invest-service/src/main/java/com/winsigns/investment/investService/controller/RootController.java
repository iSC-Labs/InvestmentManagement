package com.winsigns.investment.investService.controller;

import static org.springframework.hateoas.MediaTypes.HAL_JSON_VALUE;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.winsigns.investment.framework.hal.HALResponse;
import com.winsigns.investment.investService.resource.InstructionResource;

/**
 * Created by colin on 2017/2/22.
 */

@RestController
public class RootController {
  @GetMapping(path = "/",
      produces = {HAL_JSON_VALUE, APPLICATION_JSON_VALUE, APPLICATION_JSON_UTF8_VALUE})
  public HttpEntity<HALResponse<String>> root() {
    HALResponse<String> halResponse = new HALResponse<String>("");
    halResponse.addCollectionLink(InstructionController.class, InstructionResource.class);
    halResponse.add(linkTo((InstructionController.class)).withRel("instruct-baskets"));
    return new ResponseEntity<>(halResponse, HttpStatus.OK);
  }
}
