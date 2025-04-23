package it.lucablanchi.itemsspringapi.service;

import it.lucablanchi.itemsspringapi.model.Item;
import it.lucablanchi.itemsspringapi.repository.ItemRepository;
import it.lucablanchi.itemsspringapi.exception.NotFoundApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    public List<Item> findAll() {
        return itemRepository.findAll();
    }

    public Item findById(long id) {
        return itemRepository.findById(id)
                .orElseThrow(() -> new NotFoundApiException("Item with id " + id + " not found"));
    }

    public Item save(Item item) {
        return itemRepository.save(item);
    }

    public Item replace(long id, Item item) {
        if (!itemRepository.existsById(id)) {
            throw new NotFoundApiException("Item with id " + id + " not found");
        }

        return itemRepository.save(item);
    }

    public void deleteById(long id) {
        if (!itemRepository.existsById(id)) {
            throw new NotFoundApiException("Item with id " + id + " not found");
        }
        itemRepository.deleteById(id);
    }
}
