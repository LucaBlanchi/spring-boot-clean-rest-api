package it.lucablanchi.itemsspringapi.controller;

import it.lucablanchi.itemsspringapi.response.ApiResponse;
import it.lucablanchi.itemsspringapi.model.Item;
import it.lucablanchi.itemsspringapi.dto.ItemRequestDto;
import it.lucablanchi.itemsspringapi.service.ItemService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/items")
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<Item>>> findAll() {
        List<Item> items = itemService.findAll();
        return ResponseEntity.ok(ApiResponse.success(HttpStatus.OK.value(), "Items retrieved", items));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Item>> findById(@PathVariable long id) {
        Item item = itemService.findById(id);
        return ResponseEntity.ok(ApiResponse.success(HttpStatus.OK.value(), "Item found", item));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Item>> save(@RequestBody @Valid ItemRequestDto itemDto) {
        Item item = Item.builder()
                .name(itemDto.name())
                .description(itemDto.description())
                .price(itemDto.price())
                .build();
        Item saved = itemService.save(item);

        int statusCode = HttpStatus.CREATED.value();
        return ResponseEntity.status(statusCode)
                .body(ApiResponse.success(statusCode, "Item created", saved));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Item>> replace(@PathVariable long id, @RequestBody @Valid ItemRequestDto itemDto) {
        Item item = Item.builder()
                .id(id)
                .name(itemDto.name())
                .description(itemDto.description())
                .price(itemDto.price())
                .build();
        Item updatedItem = itemService.replace(id, item);
        return ResponseEntity.ok(ApiResponse.success(HttpStatus.OK.value(), "Item updated", updatedItem));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable long id) {
        itemService.deleteById(id);
        int statusCode = HttpStatus.NO_CONTENT.value();
        return ResponseEntity.status(statusCode)
                .body(ApiResponse.success(statusCode, "Item deleted", null));
    }
}
