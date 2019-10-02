package com.company.noteservice.dao;

import com.company.noteservice.dto.Note;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNull;

@RunWith(SpringJUnit4ClassRunner.class)
/**
 * Integrating our test with Spring.
 * Applies 'Dependency Injection' means Spring will do the instantiation of the class that the test will need.
 */
@SpringBootTest
public class NoteDaoTest {
    // dao is the implementation interface that we want Spring to wire with the test class
    @Autowired
    //@Qualifier("This bean")
    protected NoteDao dao;

    @Before
    public void setUp() throws Exception {
        // clean out the test db
        List<Note> mList = dao.getAllNotes();
        mList.stream()
                .forEach(note -> dao.deleteNote(note.getNoteId()));

    }
    @Test
    public void getGetDeleteNote() {
        Note note = new Note();
        note.setBookId(1001);
        note.setNote("Hello Kimmel!");

        // Creates note object
        note = dao.addNote(note);

        Note note2 = dao.getNote((note.getNoteId()));

        assertEquals(note, note2);

        dao.deleteNote(note.getNoteId());

        note2 = dao.getNote(note.getNoteId());

        assertNull(note2);
    }

    @Test
    public void getAllNotes() {
        Note note = new Note();  // Create note object.
        note.setBookId(1001);
        note.setNote("Hello Kimmel!");

        note = dao.addNote(note);

        // Java object is now changed from hereon. DB data not yet changed.
        note.setBookId(1002);
        note.setNote("Hi Johnny!");

        // Change Database data using update.
        dao.updateNote(note);

        Note note2 = dao.getNote(note.getNoteId());  // Create note record or rows in the database.

        assertEquals(note, note2);

    }


    @Test
    public void updateNote() {

        Note note = new Note();  // Create note object.
        note.setBookId(1001);
        note.setNote("Hello Kimmel!");

        note = dao.addNote(note);

        // Java object is now changed from hereon. DB data not yet changed.
        note.setBookId(1001);
        note.setNote("Hi only!");

        // Change Database data using update.
        dao.updateNote(note);

        Note note2 = dao.getNote(note.getNoteId());  // Create moto record or rows in the database.

        assertEquals(note, note2);
    }

    @Test
    public void getNotesbyBook(){
        Note note = new Note();  // Create moto object.
        note.setBookId(1001);
        note.setNote("Hello Kimmel!");

        Note note2 = new Note();  // Create moto2 object.
        note2.setBookId(1002);
        note2.setNote("Hi Johnny!");

        Note note3 = new Note();  // Create moto2 object.
        note3.setBookId(1001);
        note3.setNote("Hi again Kimmel!");

        // Creates moto object
        note = dao.addNote(note);  // Create moto record or rows in the database.

        // Creates moto2 object
        note2 = dao.addNote(note2);  // Create moto2 record in the database.

        // Creates moto3 object
        note3 = dao.addNote(note3);  // Create moto2 record in the database.


        List<Note> bList = dao.getNotesByBook(1001);
        assertEquals(2, bList.size());

    }

}