package br.com.paginamega.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Sorteio {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Integer concurso;
	
	private String CidadeUF;
	
	@Column(name = "data_sorteio")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Temporal(TemporalType.DATE)
	private Date dataDoSorteio;
	
	@Column(name = "dezena_1")
	private Integer dezena1;
	
	@Column(name = "dezena_2")
	private Integer dezena2;
	
	@Column(name = "dezena_3")
	private Integer dezena3;
	
	@Column(name = "dezena_4")
	private Integer dezena4;
	
	@Column(name = "dezena_5")
	private Integer dezena5;
	
	@Column(name = "dezena_6")
	private Integer dezena6;
	
	@Column(name = "numeros_par")
	private Integer numerosPar;
	
	@Column(name = "numeros_impar")
	private Integer numerosImpar;
	
	@Column(name = "ganhadores_sena")
	private Integer ganhadoresSena;
	private Integer ganhadoresQuina;
	private Integer ganhadoresQuadra;
	private Double rateioSena;
	private Double rateioQuina;
	private Double rateioQuadra;
	private String cidade;
	
	@Column(name = "arrecadacao_total")
	private Double valorAcumulado;
	private Double estimativaPremio;
	private Double acumuladoProxConcurso;

	private String eAcumulado;
	private String sorteioEspecial;
	private String observacao;
	
	public Sorteio() {
		
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public String getSorteioEspecial() {
		return sorteioEspecial;
	}

	public void setSorteioEspecial(String sorteioEspecial) {
		this.sorteioEspecial = sorteioEspecial;
	}

	public String geteAcumulado() {
		return eAcumulado;
	}

	public void seteAcumulado(String eAcumulado) {
		this.eAcumulado = eAcumulado;
	}

	public Sorteio(Integer concurso, Integer dezena1, Integer dezena2, Integer dezena3, Integer dezena4,
			Integer dezena5, Integer dezena6) {
		this.concurso = concurso;
		this.dezena1 = dezena1;
		this.dezena2 = dezena2;
		this.dezena3 = dezena3;
		this.dezena4 = dezena4;
		this.dezena5 = dezena5;
		this.dezena6 = dezena6;
	}

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

	public Integer getNumerosPar() {
		return numerosPar;
	}

	public void setNumerosPar(Integer numerosPar) {
		this.numerosPar = numerosPar;
	}

	public Integer getNumerosImpar() {
		return numerosImpar;
	}

	public void setNumerosImpar(Integer numerosImpar) {
		this.numerosImpar = numerosImpar;
	}

	public Integer getGanhadoresSena() {
		return ganhadoresSena;
	}

	public void setGanhadoresSena(Integer ganhadoresSena) {
		this.ganhadoresSena = ganhadoresSena;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getCidadeUF() {
		return CidadeUF;
	}

	public void setCidadeUF(String CidadeUF) {
		this.CidadeUF = CidadeUF;
	}

	public Double getRateioSena() {
		return rateioSena;
	}

	public void setRateioSena(Double rateioSena) {
		this.rateioSena = rateioSena;
	}

	public Integer getGanhadoresQuina() {
		return ganhadoresQuina;
	}

	public void setGanhadoresQuina(Integer ganhadoresQuina) {
		this.ganhadoresQuina = ganhadoresQuina;
	}

	public Double getRateioQuina() {
		return rateioQuina;
	}

	public void setRateioQuina(Double rateioQuina) {
		this.rateioQuina = rateioQuina;
	}

	public Integer getGanhadoresQuadra() {
		return ganhadoresQuadra;
	}

	public void setGanhadoresQuadra(Integer ganhadoresQuadra) {
		this.ganhadoresQuadra = ganhadoresQuadra;
	}

	public Double getRateioQuadra() {
		return rateioQuadra;
	}

	public void setRateioQuadra(Double rateioQuadra) {
		this.rateioQuadra = rateioQuadra;
	}

	public Double getValorAcumulado() {
		return valorAcumulado;
	}

	public void setValorAcumulado(Double valorAcumulado) {
		this.valorAcumulado = valorAcumulado;
	}

	public Double getEstimativaPremio() {
		return estimativaPremio;
	}

	public void setEstimativaPremio(Double estimativaPremio) {
		this.estimativaPremio = estimativaPremio;
	}

	public Double getacumuladoProxConcurso() {
		return acumuladoProxConcurso;
	}

	public void setacumuladoProxConcurso(Double acumuladoProxConcurso) {
		this.acumuladoProxConcurso = acumuladoProxConcurso;
	}

}