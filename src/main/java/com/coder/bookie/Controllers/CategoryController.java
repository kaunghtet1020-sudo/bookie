package com.coder.bookie.Controllers;

import org.springframework.web.bind.annotation.RestController;

import com.coder.bookie.dtos.CatDto;
import com.coder.bookie.dtos.Msg;
import com.coder.bookie.models.Category;

import com.coder.bookie.services.CatService;
import com.coder.bookie.services.ImageService;

import lombok.AllArgsConstructor;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
@AllArgsConstructor
@RequestMapping("/cats")
public class CategoryController {
    @Autowired
    private final CatService catService;
    @Autowired
    private final ImageService imageService;

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("")
    public ResponseEntity<List<Category>> allCats() {
        List<Category> cats = catService.all();
        // return new ResponseEntity<>(cats, HttpStatus.OK);
        return ResponseEntity.ok(cats);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/{id}")
    public ResponseEntity<Category> getMethodName(@PathVariable Integer id) {
        Category cat = catService.get(id);
        return ResponseEntity.ok(cat);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    // public ResponseEntity<Msg> addCat(@RequestPart(value = "file") MultipartFile
    // file, @RequestParam String name) {
    public ResponseEntity<Msg> addCat(CatDto catDto) {
        Category cat = new Category();
        cat.setName(catDto.getName());
        cat.setImage(imageService.saveFile(catDto.getFile()));
        catService.add(cat);
        Msg msg = new Msg("Category Added!", HttpStatus.OK);

        return ResponseEntity.ok(msg);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PatchMapping("/{id}")

    public ResponseEntity<Msg> patchCat(@PathVariable Integer id, CatDto catDto) {
        Category dbcat = catService.get(id);
        dbcat.setName(catDto.getName());
        dbcat.setImage(imageService.saveFile(catDto.getFile()));
        catService.update(dbcat);
        Msg msg = new Msg("Category Updated!", HttpStatus.OK);
        return ResponseEntity.ok(msg);

    }

    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping("/{id}")

    public ResponseEntity<Msg> dropcat(@PathVariable Integer id) {
        catService.drop(id);
        Msg msg = new Msg("Category deleted!", HttpStatus.OK);
        return ResponseEntity.ok(msg);
    }

}
