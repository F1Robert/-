package com.nolan.service;

import com.nolan.bean.Post;
import com.nolan.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    private final PostRepository postRepository;

    @Autowired
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public Post createPost(String content, List<String> imageDataArray) {
        Post post = new Post();
        post.setContent(content);
        post.setImageDataArray(imageDataArray);
        return postRepository.save(post);
    }

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }


    // 这里可以添加一些其他的业务逻辑或查询方法
}
