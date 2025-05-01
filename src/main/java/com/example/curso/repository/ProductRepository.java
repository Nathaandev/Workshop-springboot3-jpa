package com.example.curso.repository;
import com.example.curso.models.ProductModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
//This interface will access data and make operations (more likely to be CRUD operations)
import java.util.UUID;

@Repository //Informs to SpringBoot that this interface will access data in the database
public interface ProductRepository extends JpaRepository<ProductModel, UUID> {
}
