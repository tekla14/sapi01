package ro.sapientia2015.story.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.test.util.ReflectionTestUtils;

import ro.sapientia2015.story.CommentTestUtil;
import ro.sapientia2015.story.dto.CommentDTO;
import ro.sapientia2015.story.exception.NotFoundException;
import ro.sapientia2015.story.model.Comment;
import ro.sapientia2015.story.repository.CommentRepository;
import ro.sapientia2015.story.service.RepositoryCommentService;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNull;
import static org.mockito.Mockito.*;

public class RepositoryCommentServiceTest {

    private RepositoryCommentService service;

    private CommentRepository repositoryMock;

    @Before
    public void setUp() {
        service = new RepositoryCommentService();

        repositoryMock = mock(CommentRepository.class);
        ReflectionTestUtils.setField(service, "repository", repositoryMock);
    }

    @Test
    public void add() {
        CommentDTO dto = CommentTestUtil.createFormObject(null, CommentTestUtil.DESCRIPTION, CommentTestUtil.TITLE);

        service.add(dto);

        ArgumentCaptor<Comment> commentArgument = ArgumentCaptor.forClass(Comment.class);
        verify(repositoryMock, times(1)).save(commentArgument.capture());
        verifyNoMoreInteractions(repositoryMock);

        Comment model = commentArgument.getValue();

        assertNull(model.getId());
        assertEquals(dto.getDescription(), model.getDescription());
        assertEquals(dto.getTitle(), model.getTitle());
    }

    @Test
    public void deleteById() throws NotFoundException {
    	Comment model = CommentTestUtil.createModel(CommentTestUtil.ID, CommentTestUtil.DESCRIPTION, CommentTestUtil.TITLE);
        when(repositoryMock.findOne(CommentTestUtil.ID)).thenReturn(model);

        Comment actual = service.deleteById(CommentTestUtil.ID);

        verify(repositoryMock, times(1)).findOne(CommentTestUtil.ID);
        verify(repositoryMock, times(1)).delete(model);
        verifyNoMoreInteractions(repositoryMock);

        assertEquals(model, actual);
    }

    @Test(expected = NotFoundException.class)
    public void deleteByIdWhenIsNotFound() throws NotFoundException {
        when(repositoryMock.findOne(CommentTestUtil.ID)).thenReturn(null);

        service.deleteById(CommentTestUtil.ID);

        verify(repositoryMock, times(1)).findOne(CommentTestUtil.ID);
        verifyNoMoreInteractions(repositoryMock);
    }

    @Test
    public void findAll() {
        List<Comment> models = new ArrayList<Comment>();
        when(repositoryMock.findAll()).thenReturn(models);

        List<Comment> actual = service.findAll();

        verify(repositoryMock, times(1)).findAll();
        verifyNoMoreInteractions(repositoryMock);

        assertEquals(models, actual);
    }

    @Test
    public void findById() throws NotFoundException {
    	Comment model = CommentTestUtil.createModel(CommentTestUtil.ID, CommentTestUtil.DESCRIPTION, CommentTestUtil.TITLE);
        when(repositoryMock.findOne(CommentTestUtil.ID)).thenReturn(model);

        Comment actual = service.findById(CommentTestUtil.ID);

        verify(repositoryMock, times(1)).findOne(CommentTestUtil.ID);
        verifyNoMoreInteractions(repositoryMock);

        assertEquals(model, actual);
    }

    @Test(expected = NotFoundException.class)
    public void findByIdWhenIsNotFound() throws NotFoundException {
        when(repositoryMock.findOne(CommentTestUtil.ID)).thenReturn(null);

        service.findById(CommentTestUtil.ID);

        verify(repositoryMock, times(1)).findOne(CommentTestUtil.ID);
        verifyNoMoreInteractions(repositoryMock);
    }

    @Test
    public void update() throws NotFoundException {
        CommentDTO dto = CommentTestUtil.createFormObject(CommentTestUtil.ID, CommentTestUtil.DESCRIPTION_UPDATED, CommentTestUtil.TITLE_UPDATED);
        Comment model = CommentTestUtil.createModel(CommentTestUtil.ID, CommentTestUtil.DESCRIPTION, CommentTestUtil.TITLE);
        when(repositoryMock.findOne(dto.getId())).thenReturn(model);

        Comment actual = service.update(dto);

        verify(repositoryMock, times(1)).findOne(dto.getId());
        verifyNoMoreInteractions(repositoryMock);

        assertEquals(dto.getId(), actual.getId());
        assertEquals(dto.getDescription(), actual.getDescription());
        assertEquals(dto.getTitle(), actual.getTitle());
    }

    @Test(expected = NotFoundException.class)
    public void updateWhenIsNotFound() throws NotFoundException {
        CommentDTO dto = CommentTestUtil.createFormObject(CommentTestUtil.ID, CommentTestUtil.DESCRIPTION_UPDATED, CommentTestUtil.TITLE_UPDATED);
        when(repositoryMock.findOne(dto.getId())).thenReturn(null);

        service.update(dto);

        verify(repositoryMock, times(1)).findOne(dto.getId());
        verifyNoMoreInteractions(repositoryMock);
    }
}
