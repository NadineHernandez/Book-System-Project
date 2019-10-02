package com.company.bookservice.ViewModel;

import java.util.List;
import java.util.Objects;

public class ViewModel {
    private int bookId;
    private String title;
    private String author;
    private List<Object> notes;

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

    public List<Object> getNotes() {
        return notes;
    }

    public void setNotes(List<Object> notes) {
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
