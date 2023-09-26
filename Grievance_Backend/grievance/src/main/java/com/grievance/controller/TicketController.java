package com.grievance.controller;

import com.grievance.dto.TicketInDto;
import com.grievance.dto.TicketOutDto;
import com.grievance.dto.TicketOutWOComment;
import com.grievance.dto.TicketUpdateDto;
import com.grievance.entity.Status;
import com.grievance.service.TicketService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 */
@CrossOrigin("*")
@RestController
@RequestMapping("/ticket")
public class TicketController {
  /**
   *
   */
  @Autowired
  private TicketService ticketService;

  /**
   *
   * Controller method to create a new ticket.
   * @param ticketInDto
   * @return Responseentity with optional of TicketOut DTO.
   */
  @PostMapping("/addTicket")
  public ResponseEntity<?> saveTicket(
            @RequestBody final TicketInDto ticketInDto) {
    Optional<TicketOutDto> optionalTicketOutDto =
            ticketService.saveTicket(ticketInDto);
    return new ResponseEntity<>(optionalTicketOutDto, HttpStatus.CREATED);
  }

  /**
   * controller method to return list of all tickets.
   *
   * @param email
   * @param page
   * @param myTickets
   * @param status
   * @return ResponseEntity with optional of list of all tickets.
   */
  @GetMapping("/listAllTickets")
  public ResponseEntity<?> listAllTickets(
    @RequestHeader final String email,
    @RequestParam final Integer page,
    @RequestParam(required = false) final Status status,
    @RequestParam(required = false) final Boolean myTickets
  ) {
      Optional<List<TicketOutWOComment>> response =
          ticketService.listAllTickets(email, page, status,
              myTickets);
      return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
  }

  /**
   * controller method to return list of all tickets.
   * @param ticketId
   * @param ticketUpdateDto
   * @param email
   * @return Responseentity with optional of updated TicketOut DTO.
   */
  @PutMapping("/update")
  public ResponseEntity<?> updateTickets(
          @RequestHeader final String email,
          @RequestParam final Integer ticketId,
          @RequestBody final TicketUpdateDto ticketUpdateDto) {
      Optional<TicketOutDto>  optionalTicketOutDto = ticketService.updateTicket(
            ticketUpdateDto, ticketId, email);
      return new ResponseEntity<>(optionalTicketOutDto, HttpStatus.OK);
  }

  /**
   * controller method to return ticket by Id.
   * @param ticketId
   * @return Responseentity with optional of TicketOut DTO.
   */
  @GetMapping("/getTicket")
  public ResponseEntity<?> getTicketById(@RequestParam final Integer ticketId) {
        Optional<TicketOutDto> ticketOut = ticketService
               .findTicketByTicketId(ticketId);
         return new ResponseEntity<>(ticketOut, HttpStatus.OK);

  }

}
