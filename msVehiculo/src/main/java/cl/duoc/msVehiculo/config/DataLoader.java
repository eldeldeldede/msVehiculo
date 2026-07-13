package cl.duoc.msVehiculo.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import cl.duoc.msVehiculo.model.Marca;
import cl.duoc.msVehiculo.model.Modelo;
import cl.duoc.msVehiculo.model.TipoVehiculo;
import cl.duoc.msVehiculo.model.Vehiculo;
import cl.duoc.msVehiculo.repository.MarcaRepository;
import cl.duoc.msVehiculo.repository.ModeloRepository;
import cl.duoc.msVehiculo.repository.TipoVehiculoRepository;
import cl.duoc.msVehiculo.repository.VehiculoRepository;

@Configuration
public class DataLoader {
    @Bean
    CommandLineRunner initDataBase(VehiculoRepository vehiculoRepo,
                                   MarcaRepository marcaRepo,
                                   TipoVehiculoRepository tipoRepo,
                                   ModeloRepository modeloRepo){
                                    return args -> {


                                        if(marcaRepo.count() > 0){
                                            System.out.println("No se cargó nada porque ya hay datos");
                                        }else{
                                            Marca marca1 = new Marca(null, "KIA", "Corea del Sur");
                                            Marca marca2 = new Marca(null, "Hyundai", "Corea del Sur");
                                            Marca marca3 = new Marca(null, "Mitsubishi", "Japón");

                                            marcaRepo.save(marca1);
                                            marcaRepo.save(marca2);
                                            marcaRepo.save(marca3);
                                        
                                            Modelo modelo1 = new Modelo(null, "Morning", 2018, 5, 5, "MECANICA", "93 OCTANOS", marca1);
                                            Modelo modelo2 = new Modelo(null, "Accent", 2018, 5, 5, "MECANICA", "93 OCTANOS", marca2);
                                            Modelo modelo3 = new Modelo(null, "Lancer X", 2015, 5, 5, "MECANICA", "93 OCTANOS", marca3);

                                            modeloRepo.save(modelo1);
                                            modeloRepo.save(modelo2);
                                            modeloRepo.save(modelo3);

                                            TipoVehiculo tipo1 = new TipoVehiculo(null,"Sedán");
                                            TipoVehiculo tipo2 = new TipoVehiculo(null,"City car");
                                            TipoVehiculo tipo3 = new TipoVehiculo(null,"SUV");

                                            tipoRepo.save(tipo1);
                                            tipoRepo.save(tipo2);
                                            tipoRepo.save(tipo3);

                                            Vehiculo vehiculo1 = new Vehiculo(null, "AA-0000", 20000, modelo1, 0, "Negro", 1, tipo2);
                                            Vehiculo vehiculo2 = new Vehiculo(null, "BB-0000", 25000, modelo2, 0, "Negro", 2, tipo1);
                                            Vehiculo vehiculo3 = new Vehiculo(null, "CC-0000", 30000, modelo3, 0, "Negro", 3, tipo1);

                                            vehiculoRepo.save(vehiculo1);
                                            vehiculoRepo.save(vehiculo2);
                                            vehiculoRepo.save(vehiculo3);


                                            System.out.println("Datos cargados con éxito a la bd!");
                                        }
                                    };

                                   }
    
}
