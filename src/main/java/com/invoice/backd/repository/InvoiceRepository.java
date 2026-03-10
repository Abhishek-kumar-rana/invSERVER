package com.invoice.backd.repository;

import com.invoice.backd.entity.Invoice;
import com.invoice.backd.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {

    List<Invoice> findByClerkId(String clerkId);

    Optional<Invoice> findByClerkIdAndId(String clerkId, Long id);

    @Query("SELECT i FROM Invoice i LEFT JOIN FETCH i.items WHERE i.clerkId = :clerkId")
    List<Invoice> findInvoicesWithItems(String clerkId);
}
