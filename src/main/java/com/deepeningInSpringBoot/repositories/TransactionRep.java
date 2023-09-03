package com.deepeningInSpringBoot.repositories;

import com.deepeningInSpringBoot.domain.transaction.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRep extends JpaRepository<Transaction, Long> {
}
