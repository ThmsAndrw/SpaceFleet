import kotlin.math.max
import kotlin.math.min

/**
 * EXERCÍCIO 1: SISTEMA DE FROTA ESTELAR
 * * Pilares a aplicar:
 * - Abstração: Classe base Nave.
 * - Encapsulamento: Controle de combustível e integridade.
 * - Herança: Especializações de naves.
 * - Polimorfismo: Comportamentos de viagem distintos.
 */

abstract class Nave(val nome: String, capacidadeCombustivel: Double) {
    // Encapsulamento - Tornar privado e criar getters/setters seguros
    protected var combustivel: Double = capacidadeCombustivel
    protected var integridadeDoCasco: Int = 100
    protected var modoEmergencia: Boolean = false
    protected var distanciaRestante: Double = 0.0;

    //Abstração - Definir o contrato para viajar
    fun contrato():Boolean{
        println("Você concorda com todos os termos para iniciar a sua viage? (S/N)")
        var input: String = readln();
        if (input == "S"){
            if (integridadeDoCasco == 100 && modoEmergencia == false){
                println("Viagem autorizada e iniciada com sucesso!")
                return true
            }else{
                println("Sua nave não cumpre os requisitos para a viagem!")
                return false
            }
        }else{
            println("Aguardando futura viagem...")
            return false
        }
    }
    abstract fun viajar(distancia: Double)

    // Lógica de Dano - Implementar como a integridade é afetada
    open fun sofrerDano(impacto: Int) {
        integridadeDoCasco = max(0, integridadeDoCasco - impacto)
        if (integridadeDoCasco < 20) {
            modoEmergencia = true
            println("ALERTA: $nome em Modo de Emergência!")
        }
    }

    fun reparar(reparo: Int) {
        // Implementar lógica de reparo que desativa o modo de emergência
        if(integridadeDoCasco >= 0){
            integridadeDoCasco = min(100, integridadeDoCasco + reparo)
        }
        if (integridadeDoCasco > 20) {
            modoEmergencia = false
            println("$nome saiu do Modo de Emergência com reparo seguro")
        }
    }

    fun status() {
        println("Nave: $nome | Casco: $integridadeDoCasco% | Combustível: $combustivel | Emergência: $modoEmergencia")
    }
}

class NaveGuerra(nome: String) : Nave(nome, 500.0) {
    private var escudosAtivos: Boolean = true

    override fun viajar(distancia: Double) {
        // Polimorfismo - Implementar viagem consumindo combustível
        // Se em modo de emergência, o consumo é dobrado.
        if (modoEmergencia == false){
            combustivel = combustivel - 50
        }else{
            combustivel = combustivel - 100
        }
    }

    // Polimorfismo/Herança - Sobrescrever sofrerDano para considerar escudos
    override fun sofrerDano(impacto: Int) {
        super.sofrerDano(impacto/2)
    }
}

class NaveCientifica(nome: String) : Nave(nome, 300.0) {
    private var pontosDescoberta: Int = 0
    private var sensoresCalibrados: Boolean = true

    override fun viajar(distancia: Double) {
        // Polimorfismo - Viagem gera pontos de descoberta
        // Reduzir consumo de combustível se sensores estiverem calibrados
        pontosDescoberta += 10

        if (sensoresCalibrados == false){
            combustivel = combustivel - 100
        }else{
            combustivel = combustivel - 30
        }
    }
}

fun main() {
    // TODO: Testar o sistema criando instâncias e simulando danos e viagens
    println("=== SISTEMA DE FROTA ESTELAR ===\n")

    val naveGuerra = NaveGuerra("Destroyer-X")
    val naveCientifica = NaveCientifica("Explorer-1")

    println("Status inicial:")
    naveGuerra.status()
    naveCientifica.status()

    println("\n--- Testando contrato ---")
    if (naveGuerra.contrato()) {
        println("\n--- Viagem de 30.000 km da Nave de Guerra ---")
        naveGuerra.viajar(30.0)
        naveGuerra.status()
    }

    if (naveCientifica.contrato()) {
        println("\n--- Viagem de 30.000 km da Nave Científica ---")
        naveCientifica.viajar(30.0)
        naveCientifica.status()
    }

    println("\n--- Simulando danos ---")
    println("Nave de Guerra sofreu dano pesado!")
    naveGuerra.sofrerDano(190)
    naveGuerra.status()

    println("\nNave Científica sofreu dano moderado!")
    naveCientifica.sofrerDano(50)
    naveCientifica.status()

    println("\n--- Tentando viajar em modo de emergência ---")
    naveGuerra.viajar(30.0)
    naveGuerra.status()

    println("\n--- Realizando reparos ---")
    naveGuerra.reparar(50)
    naveGuerra.status()

    naveCientifica.reparar(30)
    naveCientifica.status()

    println("\n--- Nova viagem de 20.000 km após reparo ---")
    naveGuerra.viajar(20.0)
    naveGuerra.status()

    naveCientifica.viajar(20.0)
    naveCientifica.status()

    println("\n=== FIM DA SIMULAÇÃO ===")
}