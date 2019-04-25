package com.rybak.education.pirogok.controller;

import com.rybak.education.pirogok.domain.Post;
import com.rybak.education.pirogok.domain.User;
import com.rybak.education.pirogok.repo.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import sun.usagetracker.UsageTrackerClient;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
public class PostController {

    @Value("${upload.path}")
    private String uploadPath;

    @Autowired
    private PostRepository postRepo;

    @GetMapping("/")
    public String greeting(Map<String, Object> model) {
        return "greeting";
    }

    @PostMapping("/index")
    public String add(@AuthenticationPrincipal User user,
                      @RequestParam String text,
                      @RequestParam String tag,
                      @RequestParam("file") MultipartFile file,
                      Map<String,Object> model) throws IOException {
        Post post = new Post(text,tag,user);

        if (file != null &&  !file.getOriginalFilename().isEmpty()) {
            File uploadDir = new File(uploadPath);

            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            String uuidFile = UUID.randomUUID().toString();
            String resultFilename = uuidFile + "." + file.getOriginalFilename();

            file.transferTo(new File(uploadPath + "/" + resultFilename));

            post.setFilename(resultFilename);
        }
        postRepo.save(post);
        List<Post> posts = postRepo.findAll();
        model.put("posts",posts);
        return "index";
    }



    @GetMapping("/index")
    public String index(@RequestParam(required = false, defaultValue = "")String tag, Model model){
       List<Post> posts = postRepo.findAll();

       if(tag!=null && !tag.isEmpty()){
            posts = postRepo.findByTag(tag);
        }else {
            posts = postRepo.findAll();
        }

       model.addAttribute("tag",tag);
       model.addAttribute("posts",posts);
        return "index";
    }

}
