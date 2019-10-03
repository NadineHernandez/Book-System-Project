package com.company.bookservice.ServiceLayer;

import com.company.bookservice.DAO.BookDao;
import com.company.bookservice.DTO.Book;
import com.company.bookservice.ViewModel.ViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class ServiceLayer {

    private BookDao dao;

    @Autowired
    public ServiceLayer(BookDao dao){
        this.dao = dao;
    }

    @Transactional
    public ViewModel saveBook(ViewModel viewModel){
        return null;
    }

    private ViewModel buildViewModel(Book book){
        return null;
    }

    public ViewModel findBook(int id){
        return null;
    }

    public List<ViewModel> findAllBooks(){
        return null;
    }

    @Transactional
    public void updateBook(ViewModel viewModel){

    }

    @Transactional
    public void removeBook(int id){

    }

}
