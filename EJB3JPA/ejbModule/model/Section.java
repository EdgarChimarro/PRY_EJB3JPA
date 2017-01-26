package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the "SECTION" database table.
 * 
 */
@Entity
@Table(name="\"SECTION\"")
@NamedQuery(name="Section.findAll", query="SELECT s FROM Section s")
public class Section implements Serializable {
	private static final long serialVersionUID = 1L;
	private String id;
	private String sectionname;
	private List<Article> articles;
	private Edition edition;

	public Section() {
	}


	@Id
	@SequenceGenerator(name="SECTION_ID_GENERATOR", sequenceName="SECTION_SEQ", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SECTION_ID_GENERATOR")
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}


	public String getSectionname() {
		return this.sectionname;
	}

	public void setSectionname(String sectionname) {
		this.sectionname = sectionname;
	}


	//bi-directional many-to-one association to Article
	@OneToMany(mappedBy="section", fetch=FetchType.EAGER)
	public List<Article> getArticles() {
		return this.articles;
	}

	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}

	public Article addArticle(Article article) {
		getArticles().add(article);
		article.setSection(this);

		return article;
	}

	public Article removeArticle(Article article) {
		getArticles().remove(article);
		article.setSection(null);

		return article;
	}


	//bi-directional many-to-one association to Edition
	@ManyToOne
	@JoinColumn(name="ID", referencedColumnName="EDITION", insertable=false, updatable=false)
	public Edition getEdition() {
		return this.edition;
	}

	public void setEdition(Edition edition) {
		this.edition = edition;
	}

}