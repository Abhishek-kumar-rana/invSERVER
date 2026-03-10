package com.invoice.backd.service;


import com.invoice.backd.entity.Invoice;
import com.invoice.backd.entity.InvoiceItem;
import com.invoice.backd.repository.InvoiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InvoiceServices {

    private final InvoiceRepository invoiceRepository;

    public Invoice saveInvoice(Invoice invoice) {

        if (invoice.getItems() != null && !invoice.getItems().isEmpty()) {
            invoice.getItems().forEach(item -> item.setInvoice(invoice));
        }

        return invoiceRepository.save(invoice);
    }

    public List<Invoice> fetchInvoices(String clerkId){

        List<Invoice> inv = invoiceRepository.findInvoicesWithItems(clerkId);



        return inv;
    }

    public void removeInvoice(Long invoiceId, String clerkId){

        System.out.println("remove service is called! ");
        Invoice existingInvoice = invoiceRepository
                .findByClerkIdAndId(clerkId, invoiceId)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Invoice not found"));
        if(existingInvoice.getId()==null){
            System.out.println("invoice id is null");
        }
        else System.out.println("invoice id is : "+existingInvoice.getId());

        invoiceRepository.delete(existingInvoice);
    }
}