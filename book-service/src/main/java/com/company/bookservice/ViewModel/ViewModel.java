package com.company.bookservice.ViewModel;

import com.company.bookservice.util.messages.Note;

import java.util.List;
import java.util.Objects;

public class ViewModel {
    private int bookId;
    private String title;
    private String author;
    private List<Note> notes;

    public ViewModel(int bookId, String title, String author, List<Note> notes) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.notes = notes;
    }

    public ViewModel(){}

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public List<Note> getNotes() {
        return notes;
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ViewModel viewModel = (ViewModel) o;
        return bookId == viewModel.bookId &&
                title.equals(viewModel.title) &&
                author.equals(viewModel.author) &&
                Objects.equals(notes, viewModel.notes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookId, title, author, notes);
    }
}
