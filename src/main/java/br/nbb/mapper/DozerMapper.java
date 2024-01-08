package br.nbb.mapper;

import java.util.ArrayList;
import java.util.List;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;

public class DozerMapper {
	
	private static Mapper mapper = DozerBeanMapperBuilder.buildDefault();
	
	public static <O, D> D parseObject(O Origem, Class<D> Destino) {
		return mapper.map(Origem, Destino);
	}
	
	public static <O, D> List<D> parseListObject(List<O> Origem, Class<D> Destino) {
		List<D> objetosdeDestino = new ArrayList<>();
		
		for (O o : Origem) {
			objetosdeDestino.add(mapper.map(o, Destino));
		}
		return objetosdeDestino;
	}

}
