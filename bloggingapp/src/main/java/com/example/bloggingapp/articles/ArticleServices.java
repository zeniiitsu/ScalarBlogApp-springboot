package com.example.bloggingapp.articles;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bloggingapp.articles.dtos.CreateArticleRequest;
import com.example.bloggingapp.articles.dtos.UpdateArticleRequest;
import com.example.bloggingapp.users.UserEntity;
import com.example.bloggingapp.users.UserRepository;
import com.example.bloggingapp.users.UserServices;
import com.example.bloggingapp.users.UserServices.UserNotFoundException;

@Service
public class ArticleServices {
	
	@Autowired
	private ArticleRepository articleRepository;
	@Autowired
	private UserRepository userRepository;
	
	/**
	 * @return
	 */
	public Iterable<ArticleEntity> getAllArticles(){
		return this.articleRepository.findAll();
	}
	
	/**
	 * @param slug
	 * @return
	 */
	public ArticleEntity getArticleBySlug(String slug) {
		var article = this.articleRepository.findBySlug(slug);
		if(article == null) {
			throw new ArticleNotFoundException(slug);
		}
		return article;
		
	}
	
	/**
	 * @param article
	 * @return
	 */
	public ArticleEntity createArticle(CreateArticleRequest article, Long authorId) {
		UserEntity author = userRepository.findById(authorId).orElseThrow(()-> new UserServices.UserNotFoundException(authorId));

        return articleRepository.save(ArticleEntity.builder()
                .title(article.getTitle())
                // TODO: create a proper slugification function
                .slug(article.getTitle().toLowerCase().replaceAll("\\s+", "-"))
                .body(article.getBody())
                .subtitle(article.getSubtitle())
                .author(author)
                .build()
        );
		
	}
	
    public ArticleEntity updateArticle(Long articleId, UpdateArticleRequest a) {
        var article = articleRepository.findById(articleId).orElseThrow(() -> new ArticleNotFoundException(articleId));

        if (a.getTitle() != null) {
            article.setTitle(a.getTitle());
            article.setSlug(a.getTitle().toLowerCase().replaceAll("\\s+", "-"));
        }

        if (a.getBody() != null) {
            article.setBody(a.getBody());
        }

        if (a.getSubtitle() != null) {
            article.setSubtitle(a.getSubtitle());
        }

        return articleRepository.save(article);
    }
	
    static class ArticleNotFoundException extends IllegalArgumentException {
        /**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public ArticleNotFoundException(String slug) {
            super("Article " + slug + " not found");
        }

        public ArticleNotFoundException(Long id) {
            super("Article with id: " + id + " not found");
        }
    }

}
