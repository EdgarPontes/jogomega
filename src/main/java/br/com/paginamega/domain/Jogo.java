package br.com.paginamega.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Jogo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull(message="Preencha o número do concurso")
	private Integer concurso;
	
	@NotNull(message="Preencha a data do sorteio")
	@Column(name = "data_sorteio")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Temporal(TemporalType.DATE)
	private Date dataDoSorteio;
	
	@NotNull(message="Preencha o número da dezena 1")
	@Column(name = "dezena_1")
	private Integer dezena1;
	
	@NotNull(message="Preencha o número da dezena 2")
	@Column(name = "dezena_2")
	private Integer dezena2;
	
	@NotNull(message="Preencha o número da dezena 3")
	@Column(name = "dezena_3")
	private Integer dezena3;
	
	@NotNull(message="Preencha o número da dezena 4")
	@Column(name = "dezena_4")
	private Integer dezena4;
	
	@NotNull(message="Preencha o número da dezena 5")
	@Column(name = "dezena_5")
	private Integer dezena5;
	
	@NotNull(message="Preencha o número da dezena 6")
	@Column(name = "dezena_6")
	private Integer dezena6;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getConcurso() {
		return concurso;
	}

	public void setConcurso(Integer concurso) {
		this.concurso = concurso;
	}

	public Date getDataDoSorteio() {
		return dataDoSorteio;
	}

	public void setDataDoSorteio(Date dataDoSorteio) {
		this.dataDoSorteio = dataDoSorteio;
	}

	public Integer getDezena1() {
		return dezena1;
	}

	public void setDezena1(Integer dezena1) {
		this.dezena1 = dezena1;
	}

	public Integer getDezena2() {
		return dezena2;
	}

	public void setDezena2(Integer dezena2) {
		this.dezena2 = dezena2;
	}

	public Integer getDezena3() {
		return dezena3;
	}

	public void setDezena3(Integer dezena3) {
		this.dezena3 = dezena3;
	}

	public Integer getDezena4() {
		return dezena4;
	}

	public void setDezena4(Integer dezena4) {
		this.dezena4 = dezena4;
	}

	public Integer getDezena5() {
		return dezena5;
	}

	public void setDezena5(Integer dezena5) {
		this.dezena5 = dezena5;
	}

	public Integer getDezena6() {
		return dezena6;
	}

	public void setDezena6(Integer dezena6) {
		this.dezena6 = dezena6;
	}

	@Override
	public String toString() {
		return "Jogo [id=" + id + ", concurso=" + concurso + ", dataDoSorteio=" + dataDoSorteio + ", dezena1=" + dezena1
				+ ", dezena2=" + dezena2 + ", dezena3=" + dezena3 + ", dezena4=" + dezena4 + ", dezena5=" + dezena5
				+ ", dezena6=" + dezena6 + "]";
	}
	
}
