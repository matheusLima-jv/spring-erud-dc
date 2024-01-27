package br.nbb.dataVO.v1;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.dozermapper.core.Mapping;

public class BooksVO extends RepresentationModel<BooksVO> implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@JsonProperty("id")
	@Mapping("id")
	private Long id;
	@JsonProperty("autor")
	private String author;
	@JsonProperty("data_lancamento")
	private Date launch_date;
	@JsonProperty("valor")
	private Double price;
	@JsonProperty("titulo")
	private String title;
	
	public BooksVO() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Date getLaunch_date() {
		return launch_date;
	}

	public void setLaunch_date(Date launch_date) {
		this.launch_date = launch_date;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(author, id, launch_date, price, title);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		BooksVO other = (BooksVO) obj;
		return Objects.equals(author, other.author) && Objects.equals(id, other.id)
				&& Objects.equals(launch_date, other.launch_date) && Objects.equals(price, other.price)
				&& Objects.equals(title, other.title);
	}
	
	
	

}
