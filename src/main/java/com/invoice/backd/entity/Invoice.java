package com.invoice.backd.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "invoices")
@EntityListeners(AuditingEntityListener.class)
@ToString(exclude = "items")
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String clerkId;
    private String invoiceTitle;

    @Column(columnDefinition = "TEXT")
    private String logo;
    private String thumbnailUrl;
    private String invoiceNumber;
    private LocalDate invoiceDate;
    private LocalDate dueDate;

    private String billTo;
    private String shipTo;

    @JsonManagedReference
    @OneToMany(mappedBy = "invoice", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<InvoiceItem> items;

    private String bankName;
    private String accountNumber;
    private String ifsc;

    private String notes;
    private double taxRate;

    private String selectedTemplate;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;      // ✅ auto set on INSERT

    @LastModifiedDate
    private LocalDateTime lastUpdatedAt;  // ✅ auto set on UPDATE
}