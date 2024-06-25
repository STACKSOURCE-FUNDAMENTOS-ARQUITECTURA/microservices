package com.techlinker.employment_service;

import com.techlinker.employment_service.entities.Post;
import com.techlinker.employment_service.repository.IPostRepository;
import com.techlinker.employment_service.service.impl.PostServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
@SpringBootTest
class EmploymentServiceApplicationTests  {

	@Mock
	private IPostRepository postRepository;

	@InjectMocks
	private PostServiceImpl postService;

	@Test
	public void testGetPostNotFound()  throws Exception {
		when(postRepository.findById(1L)).thenReturn(Optional.empty());

		Optional<Post> result = postService.getById(1L);

		assertFalse(result.isPresent());
		verify(postRepository, times(1)).findById(1L);
	}

	@Test
	public void testSavePost()  throws Exception {
		Post postToSave = new Post(1L, "Test Title", "Test Description", "Test ImageUrl", 1L);
		when(postRepository.save(any(Post.class))).thenReturn(postToSave);

		Post result = postService.save(postToSave);

		assertEquals(postToSave, result);
		verify(postRepository, times(1)).save(postToSave);
	}

	@Test
	public void testDeletePost()  throws Exception {
		Post postToDelete = new Post(1L, "Test Title", "Test Description", "Test ImageUrl", 1L);
		doNothing().when(postRepository).delete(postToDelete);

		postService.delete(postToDelete.getId());

		verify(postRepository, times(1)).delete(postToDelete);
	}

}
