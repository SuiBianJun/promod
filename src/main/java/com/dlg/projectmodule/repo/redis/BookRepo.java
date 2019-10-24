package com.dlg.projectmodule.repo.redis;

import com.dlg.projectmodule.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepo extends JpaRepository<Book, String>, JpaSpecificationExecutor<Book> {
}
