import React, { Component } from 'react';
import {
  SafeAreaView,
  StyleSheet,
  View,
  Text,
  TextInput,
} from 'react-native';


class CurrencyInput extends React.Component {
  constructor(props) {
    super(props);
    this.handleChange = this.handleChange.bind(this);

    this.currencyNames = {
      usd: 'USD',
      eur: 'Euro'
    };
  }

  handleChange(e) {
    this.props.onAmountChange(e);
  }

  render() {
    const amount = this.props.amount;
    const currency = this.props.currency;
    return (
      <View>
        <Text style={styles.title}>Enter amount in {this.currencyNames[currency]}:</Text>
        <TextInput
          style={styles.input}
          value={amount}
          onChangeText={this.handleChange} />
      </View>
    );
  }
}

export default class App extends Component {
  constructor(props) {
    super(props)
    this.state = {amount: '', currency: 'eur'};
    this.handleEURChange = this.handleEURChange.bind(this);
    this.handleUSDChange = this.handleUSDChange.bind(this);
  }

  handleEURChange(amount) {
    this.setState({currency: 'eur', amount});
  }

  handleUSDChange(amount) {
    this.setState({currency: 'usd', amount});
  }

  toEUR(usd) {
    return usd * 0.86;
  }
  
  toUSD(eur) {
    return eur * 1.16;
  }

  tryConvert(amount, convert) {
    const input = parseFloat(amount);
    if (Number.isNaN(input)) {
      return '';
    }
    const converted = convert(input);
    return parseFloat(converted).toFixed(2).toString();
  }

  financeAdvice(amount) {
    return (amount >= 100) ? "That's a lot." : "Please save some money.";
  }

  render() {
    const currency = this.state.currency;
    const amount = this.state.amount;
    const eur = currency === 'usd' ? this.tryConvert(amount, this.toEUR) : amount;
    const usd = currency === 'eur' ? this.tryConvert(amount, this.toUSD) : amount;
    const adviceAmount = currency === 'usd' ? eur : usd;

    return (
      <SafeAreaView style={styles.container}>
        <CurrencyInput
          currency="eur"
          amount={eur}
          onAmountChange={this.handleEURChange} />
        <CurrencyInput
          currency="usd"
          amount={usd}
          onAmountChange={this.handleUSDChange} />
        <Text style={styles.advice}>{this.financeAdvice(adviceAmount)}</Text>
      </SafeAreaView>
    );
  }
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
    padding: 50,
  },
  input: {
    height: 40,
    marginVertical: 5,
    marginHorizontal: 10,
    borderWidth: 1,
    padding: 10,
  },
  title: {
    color: '#000',
    textAlign: 'left',
    marginVertical: 5,
  },
  advice: {
    color: '#90e',
    textAlign: 'center',
    marginVertical: 10,
  },
});
