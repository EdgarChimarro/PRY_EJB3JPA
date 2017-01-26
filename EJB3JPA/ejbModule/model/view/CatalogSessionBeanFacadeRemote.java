package model.view;

import java.util.List;

import javax.ejb.Remote;

import model.Article;
import model.Catalog;
import model.Edition;
import model.Section;

@Remote
public interface CatalogSessionBeanFacadeRemote {

	public List<Edition> getAllEditions();

	public List<Article> getAllArticles();

	public List<Catalog> getAllCatalogs();

	public List<Edition> getCatalogEditions(Catalog catalog);

	public List<Section> getEditionSections(Edition edition);

	public List<Article> getSectionArticles(Section section);

	public void createTestData();

	public void deleteSomeData();

	public void removeEdition(Edition edition);

	public void removeSection(Section section);

	public void removeArticle(Article article);
}