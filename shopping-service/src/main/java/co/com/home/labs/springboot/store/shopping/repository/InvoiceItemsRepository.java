package co.com.home.labs.springboot.store.shopping.repository;

import co.com.home.labs.springboot.store.shopping.entity.InvoiceItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceItemsRepository extends JpaRepository<InvoiceItem,Long> {
}
