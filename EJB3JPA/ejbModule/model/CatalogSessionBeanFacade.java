package model;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import model.view.CatalogSessionBeanFacadeRemote;

/**
 * Session Bean implementation class CatalogSessionBeanFacade
 */
@Stateless(name = "CatalogSessionBean", mappedName = "EJB3-SessionEJB")
@Remote(CatalogSessionBeanFacadeRemote.class)
public class CatalogSessionBeanFacade implements CatalogSessionBeanFacadeRemote {

	/**
	 * Default constructor.
	 */
	public CatalogSessionBeanFacade() {
		// TODO Auto-generated constructor stub
	}

	@PersistenceContext(unitName = "em")
	EntityManager em;

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public List<Edition> getAllEditions() {
		ArrayList<Edition> editions = new ArrayList<Edition>();
		Query q = em.createNamedQuery("Edition.findAll");
		for (Object ed : q.getResultList()){
			editions.add((Edition) ed);
		}
		return editions;
	}

	public void createTestData() {

		// create catalog for Oracle Magazine
		Catalog catalog1 = new Catalog();
		catalog1.setJournal("Oracle Magazine");
		em.persist(catalog1);
		em.flush();

		// Add an Edition
		Edition edition1 = new Edition();
		edition1.setEdition("January February 2009");
		em.persist(edition1);
		em.flush();
		em.merge(catalog1);
		catalog1.addEdition(edition1);

		//Add a features section
		Section features = new Section();
		features.setSectionname("FEATURES");
		em.persist(features);
		em.merge(edition1);
		edition1.addSection(features);
		
		// Add an article to Features section
		Article article = new Article();
		article.setTitle("Launching performance");
		article.setSection(features);
		em.persist(article);
		em.merge(features);
		features.addArticle(article);
		em.flush();
		
		//Add a technology section
		Section technology = new Section();
		technology.setSectionname("Technology");
		em.persist(technology);
		em.merge(edition1);
		edition1.addSection(technology);
		
		//add an article to Technology section
		article = new Article();
		article.setSection(technology);
		article.setTitle("On dinamyc Sampling");
		em.persist(article);
		em.merge(technology);
		technology.addArticle(article);
		em.flush();
		
	}
	
	public void deleteSomeData(){
		
		//remove an article
		Query q = em.createNamedQuery("findArticleByTitle");
		q.setParameter("title", "Launching performance");
		List lista = q.getResultList();
		for (Object article : lista){
			em.remove(article);
		}
	}
	
	public void removeEdition(Edition edition){
		Catalog catalog = edition.getCatalog();
		catalog.removeEdition(edition);
		em.remove(edition);
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public List<Article> getAllArticles() {
		Query q = em.createNamedQuery("Articles.findAll");
	    List<Article> arcticles = q.getResultList();
	    ArrayList<Article> articleList = new ArrayList<Article>(arcticles.size());
	    for (Article articulo : arcticles) {
	      articleList.add(articulo);
	    }
	    return articleList;
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public List<Catalog> getAllCatalogs() {
		Query q = em.createNamedQuery("Catalog.findAll");
	    List<Catalog> catalogs = q.getResultList();
	    ArrayList<Catalog> catalogList = new ArrayList<Catalog>(catalogs.size());
	    for (Catalog catalog : catalogs) {
	      catalogList.add(catalog);
	    }
	    return catalogList;
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public List<Edition> getCatalogEditions(Catalog catalog) {
		// TODO Auto-generated method stub
		return null;
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public List<Section> getEditionSections(Edition edition) {
		// TODO Auto-generated method stub
		return null;
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public List<Article> getSectionArticles(Section section) {
		// TODO Auto-generated method stub
		return null;
	}

	 public void removeSection(Section section) {
	    Edition edition = section.getEdition();
	    edition.removeSection(section);
	    em.remove(section);
	  }

	  public void removeArticle(Article article) {
	    Section section = article.getSection();
	    section.removeArticle(article);
	    em.remove(article);
	  }
	
}
