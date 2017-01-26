package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the "CATALOG" database table.
 * 
 */
@Entity
@Table(name="\"CATALOG\"")
@NamedQuery(name="Catalog.findAll", query="SELECT c FROM Catalog c")
public class Catalog implements Serializable {
	private static final long serialVersionUID = 1L;
	private long id;
	private String journal;
	private List<Edition> editions;

	public Catalog() {
	}


	@Id
	@SequenceGenerator(name="CATALOG_ID_GENERATOR", sequenceName="CATALOG_SEQ", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.IDENTITY, generator="CATALOG_ID_GENERATOR")
	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}


	public String getJournal() {
		return this.journal;
	}

	public void setJournal(String journal) {
		this.journal = journal;
	}


	//bi-directional many-to-one association to Edition
	@OneToMany(mappedBy="catalog", cascade={CascadeType.ALL}, fetch=FetchType.EAGER)
	public List<Edition> getEditions() {
		return this.editions;
	}

	public void setEditions(List<Edition> editions) {
		this.editions = editions;
	}

	public Edition addEdition(Edition edition) {
		getEditions().add(edition);
		edition.setCatalog(this);

		return edition;
	}

	public Edition removeEdition(Edition edition) {
		getEditions().remove(edition);
		edition.setCatalog(null);

		return edition;
	}

}