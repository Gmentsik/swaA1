package db.controllers;

import dao.NewsReaderEJB;
import db.entities.user;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gergely on 25.03.2015.
 */
@Named(value = "nReaderController")
@RequestScoped
public class nReaderController {
    @EJB
    private NewsReaderEJB nReaderEJB;
    private user nReader = new user();
    private List<user> nReaderList = new ArrayList<>();

    public List<user> getNewsReaderList() {
        nReaderList = nReaderEJB.findNewsReaders();
        return nReaderList;
    }

    public String viewNewsReader(){
        return "readerList.xhtml";
    }

    public String addNewReader() {
        nReader = nReaderEJB.addNew(nReader);
        nReaderList = nReaderEJB.findNewsReaders();
        return "readerList.xhtml";
    }

    public user getnReader() {
        return nReader;
    }

    public void setnReader(user nReader) {
        this.nReader = nReader;
    }

    public NewsReaderEJB getnReaderEJB() {
        return nReaderEJB;
    }

    public void setnReaderEJB(NewsReaderEJB nReaderEJB) {
        this.nReaderEJB = nReaderEJB;
    }

    public List<user> getnReaderList() {
        return nReaderList;
    }

    public void setnReaderList(List<user> nReaderList) {
        this.nReaderList = nReaderList;
    }
}
