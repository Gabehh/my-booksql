package com.aamv.mybookql.repository;

import com.aamv.mybookql.model.Book;
import org.springframework.cloud.gcp.data.firestore.FirestoreReactiveRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends FirestoreReactiveRepository<Book> {
}
