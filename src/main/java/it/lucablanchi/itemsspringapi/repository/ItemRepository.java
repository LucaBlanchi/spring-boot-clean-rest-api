package it.lucablanchi.itemsspringapi.repository;

import it.lucablanchi.itemsspringapi.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
