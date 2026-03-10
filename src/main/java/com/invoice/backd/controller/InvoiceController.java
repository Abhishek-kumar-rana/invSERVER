package com.invoice.backd.controller;


import com.invoice.backd.entity.Invoice;
import com.invoice.backd.service.InvoiceServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/invoices")
@RequiredArgsConstructor
public class InvoiceController {

    private final InvoiceServices invoiceServices;

    @PostMapping("/insert")
    public ResponseEntity<Invoice> saveInvoice(@RequestBody Invoice invoice) {
//        return ResponseEntity.ok(invoice);

        return ResponseEntity.ok(invoiceServices.saveInvoice(invoice));
    }

    @GetMapping("/hello")
    public String say() {
        return "Hello+";
    }

    @GetMapping("/fetch")
    public ResponseEntity<List<Invoice>>  fetchInvoices(Authentication authentication){
        System.out.println("backend fetch is called");
        List<Invoice> inv = invoiceServices.fetchInvoices(authentication.getName());
//        System.out.println(inv);
        return ResponseEntity.ok(inv);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInvoice(@PathVariable Long id,Authentication authentication){
        System.out.println("Delete mapping is called! ");
        if(authentication != null && authentication.getName() != null){
            invoiceServices.removeInvoice(id,authentication.getName());
            return ResponseEntity.noContent().build();
        }
        throw new ResponseStatusException(HttpStatus.FORBIDDEN,"User does not have permission to access this resources");
    }
}
//rCtgrspzOuVF889Y