package com.fcastillo.capitulo.primefaces.controller;

import com.fcastillo.capitulo.primefaces.ejb.GraficosFacadeLocal;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;

/**
 *
 * @author fcastillo
 */
@Named(value = "graficosController")
@RequestScoped
public class GraficosController {

    @EJB
    GraficosFacadeLocal graficosFacade;

    private BarChartModel barModel;

    public BarChartModel getBarModel() {
        return barModel;
    }

    public void setBarModel(BarChartModel barModel) {
        this.barModel = barModel;
    }

    @PostConstruct
    public void init() {
        crearBarModel();
    }

    private void crearBarModel() {
        barModel = new BarChartModel();
        
        List<Object[]> lista = graficosFacade.cantCarrerasPorFacultad();

        ChartSeries serieCantidad = new ChartSeries();
        serieCantidad.setLabel("Facultades");
        
        lista.stream().forEach(x -> {
            String name = (String) x[0];
            int count = ((Number) x[1]).intValue();
            serieCantidad.set(name, count);
        });
        
        barModel.addSeries(serieCantidad);
        barModel.setTitle("Cantidad de carreras por facultad");
        barModel.setLegendPosition("ne");

        Axis yAxis = barModel.getAxis(AxisType.Y);
        Axis xAxis = barModel.getAxis(AxisType.X);
        xAxis.setLabel("Facultades");
        yAxis.setLabel("Cantidad");
    }
}
