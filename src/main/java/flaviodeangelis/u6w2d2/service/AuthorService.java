package flaviodeangelis.u6w2d2.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import flaviodeangelis.u6w2d2.entities.Author;
import flaviodeangelis.u6w2d2.exception.BadRequestException;
import flaviodeangelis.u6w2d2.exception.NotFoundException;
import flaviodeangelis.u6w2d2.payload.NewAuthorDTO;
import flaviodeangelis.u6w2d2.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class AuthorService {
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private Cloudinary cloudinary;

    public Author save(NewAuthorDTO body) {
        authorRepository.findByEmail(body.email()).ifPresent(author -> {
            try {
                throw new BadRequestException("Autore come email " + author.getEmail() + " già esistente");
            } catch (BadRequestException e) {
                throw new RuntimeException(e);
            }
        });
        Author author = new Author();
        author.setAvatar("http://ui-avatars.com/api/?name=" + body.name() + "+" + body.surname());
        author.setName(body.name());
        author.setSurname(body.surname());
        author.setEmail(body.email());
        author.setBirthDate(body.birthDate());
        return authorRepository.save(author);
    }

    public List<Author> getAuthors() {
        return authorRepository.findAll();
    }

    public Author findById(long id) throws NotFoundException {
        return authorRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public void findByIdAndDelete(long id) throws NotFoundException {
        Author found = this.findById(id);
        authorRepository.delete(found);
    }

    public Author findByIdAndUpdate(long id, Author body) throws NotFoundException {
        Author found = this.findById(id);
        found.setId(id);
        found.setName(body.getName());
        found.setSurname(body.getSurname());
        found.setEmail(body.getEmail());
        found.setBirthDate(body.getBirthDate());
        found.setAvatar(body.getAvatar());
        return authorRepository.save(found);
    }

    public String uploadAvatar(MultipartFile file, long id, Author body) throws IOException, NotFoundException {
        Author found = this.findById(id);
        String avatarURL = (String) cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap()).get("url");
        found.setId(id);
        found.setName(body.getName());
        found.setSurname(body.getSurname());
        found.setEmail(body.getEmail());
        found.setBirthDate(body.getBirthDate());
        found.setAvatar(avatarURL);
        authorRepository.save(found);
        return avatarURL;
    }
}
