package org.cook.lab9.controller;

import lombok.RequiredArgsConstructor;
import org.cook.lab9.model.Country;
import org.cook.lab9.model.Item;
import org.cook.lab9.service.impl.ItemServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/items")
@RequiredArgsConstructor
public class ItemApiController {

    private final ItemServiceImpl itemService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getItemById(@PathVariable Long id){
        Item item = itemService.getItemById(id);

        return ResponseEntity.ok(item);
    }

    @GetMapping
    public ResponseEntity<?> getAllItems(){
        List<Item> items = itemService.getAllItems();

        return ResponseEntity.ok(items);
    }

    @PostMapping
    public ResponseEntity<?> createItem(@RequestBody Item model){
        Item item = itemService.createItem(model);

        return ResponseEntity.ok(item);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateItem(@PathVariable Long id, @RequestBody Item model){
        Item item = itemService.updateItem(id, model);

        return ResponseEntity.ok(item);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteItem(@PathVariable Long id){
        itemService.deleteItem(id);

        return ResponseEntity.noContent()
                .build();
    }
}
