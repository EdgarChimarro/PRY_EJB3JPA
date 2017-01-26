package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the EDITION database table.
 * 
 */
@Entity
@NamedQuery(name="Edition.findAll", query="SELECT ed FROM Edition ed ")
public class Edition implements Serializable {
	private static final long serialVersionUID = 1L;
	private long id;
	private String edition;
	private Catalog catalog;
	private List<Section> sections;

	public Edition() {
	}


	@Id
	@SequenceGenerator(name="EDITION_ID_GENERATOR", sequenceName="EDITION_SEQ", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.IDENTITY, generator="EDITION_ID_GENERATOR")
	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}


	//bi-directional many-to-one association to Catalog
	@ManyToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
	@JoinColumn(name="EDITION", insertable=false, updatable=false)
	public Catalog getCatalog() {
		return this.catalog;
	}

	public void setCatalog(Catalog catalog) {
		this.catalog = catalog;
	}


	//bi-directional many-to-one association to Section
	@OneToMany(mappedBy="edition", fetch=FetchType.EAGER)
	public List<Section> getSections() {
		return this.sections;
	}

	public void setSections(List<Section> sections) {
		this.sections = sections;
	}

	public Section addSection(Section section) {
		getSections().add(section);
		section.setEdition(this);

		return section;
	}
	
	public String getEdition() {
        return edition;
    }
    
	public void setEdition(String edition) {
        this.edition = edition;
    }

	public Section removeSection(Section section) {
		getSections().remove(section);
		section.setEdition(null);

		return section;
	}

}