package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the ARTICLE database table.
 * 
 */
@Entity

@NamedQueries({
	@NamedQuery(name="Article.findAll", query="SELECT a FROM Article a"),
	@NamedQuery(name="findArticleByTitle", query="SELECT a FROM Article a where a.title = :title")	
})


public class Article implements Serializable {
	private static final long serialVersionUID = 1L;
	private long id;
	private String title;
	private Section section;

	public Article() {
	}


	@Id
	@SequenceGenerator(name="ARTICLE_ID_GENERATOR", sequenceName="ARTICLE_SEQ", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ARTICLE_ID_GENERATOR")
	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}


	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}


	//bi-directional many-to-one association to Section
	@ManyToOne
	@JoinColumn(name="ID", insertable=false, updatable=false)
	public Section getSection() {
		return this.section;
	}

	public void setSection(Section section) {
		this.section = section;
	}

}