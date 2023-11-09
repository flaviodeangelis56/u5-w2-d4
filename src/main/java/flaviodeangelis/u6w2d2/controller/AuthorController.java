package flaviodeangelis.u6w2d2.controller;

import flaviodeangelis.u6w2d2.entities.Author;
import flaviodeangelis.u6w2d2.exception.BadRequestException;
import flaviodeangelis.u6w2d2.exception.NotFoundException;
import flaviodeangelis.u6w2d2.payload.NewAuthorDTO;
import flaviodeangelis.u6w2d2.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


@RestController
@RequestMapping("/author")
public class AuthorController {
    @Autowired
    private AuthorService authorService;

    @GetMapping("")
    public List<Author> getBlogPosts() {
        return authorService.getAuthors();
    }

    @GetMapping("/{id}")
    public Author findById(@PathVariable long id) throws NotFoundException {
        return authorService.findById(id);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public Author sevePost(@RequestBody @Validated NewAuthorDTO body, BindingResult validation) throws BadRequestException {
        if (validation.hasErrors()) {
            throw new BadRequestException(validation.getAllErrors());
        } else {
            return authorService.save(body);
        }

    }

    @PutMapping("/{id}")
    public Author findByIdAndUpdate(@PathVariable long id, @RequestBody Author body) throws NotFoundException {
        return authorService.findByIdAndUpdate(id, body);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findByIdAndDelete(@PathVariable long id) throws NotFoundException {
        authorService.findByIdAndDelete(id);
    }

    @PutMapping("/uploadAvatar/{id}")
    public String uploadTest(@RequestParam("avatar") MultipartFile body, @PathVariable long id) throws IOException, NotFoundException {
        Author author = authorService.findById(id);
        return authorService.uploadAvatar(body, id, author);
    }
}
