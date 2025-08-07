package com.coder.bookie.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.coder.bookie.dtos.BookDto;
import com.coder.bookie.dtos.Msg;
import com.coder.bookie.models.Book;
import com.coder.bookie.services.BookService;
import com.coder.bookie.services.CatService;
import com.coder.bookie.services.ImageService;

import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping("/books")
@AllArgsConstructor
public class BookController {
    @Autowired
    private final BookService bookService;
    @Autowired
    private final ImageService imageService;
    @Autowired
    private final CatService catService;

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("")
    public ResponseEntity<List<Book>> Allbooks() {
        List<Book> books = bookService.all();
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/{id}")
    public ResponseEntity<Book> singleBook(@PathVariable Long id) {
        return ResponseEntity.ok(bookService.get(id));

    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    // public ResponseEntity<Msg> addBook(@RequestPart(value = "file") MultipartFile
    // file,
    // @RequestParam String title,
    // @RequestParam String author,
    // @RequestParam String publicDate,
    // @RequestParam String description,
    // @RequestParam double price,
    // @RequestParam Integer stock,
    // @RequestParam Integer catId) {
    public ResponseEntity<Msg> addBook(BookDto bookDto) {
        Book book = new Book();
        book.setTitle(bookDto.getTitle());
        book.setAuthor(bookDto.getAuthor());
        book.setPublicDate(bookDto.getPublicDate());
        book.setDescription(bookDto.getDescription());
        book.setPrice(bookDto.getPrice());
        book.setStock(bookDto.getStock());
        book.setImage(imageService.saveFile(bookDto.getFile()));
        book.setCategory(catService.get(bookDto.getCatid())); // category idကို book category fieldထဲသို့ထည့်ပေးသည်
        bookService.add(book);
        Msg msg = new Msg("Book saved success", HttpStatus.OK);
        return ResponseEntity.ok(msg);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PatchMapping("/{id}")
    public ResponseEntity<Msg> updateBook(@PathVariable Long id, BookDto bookDto) {
        Book dBook = bookService.get(id);
        dBook.setTitle(bookDto.getTitle());
        dBook.setAuthor(bookDto.getAuthor());
        dBook.setPublicDate(bookDto.getPublicDate());
        dBook.setDescription(bookDto.getDescription());
        dBook.setPrice(bookDto.getPrice());
        dBook.setStock(bookDto.getStock());
        dBook.setImage(imageService.saveFile(bookDto.getFile()));
        dBook.setCategory(catService.get(bookDto.getCatid()));
        bookService.update(dBook);
        Msg msg = new Msg("Book update success", HttpStatus.OK);
        return ResponseEntity.ok(msg);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping("/{id}")
    public ResponseEntity<Msg> deleteBook(@PathVariable Long id) {
        bookService.drop(id);
        Msg msg = new Msg("Book delete success", HttpStatus.OK);
        return ResponseEntity.ok(msg);
    }
}
